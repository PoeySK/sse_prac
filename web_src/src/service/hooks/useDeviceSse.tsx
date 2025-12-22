import { useQueryClient } from "@tanstack/react-query";
import { useEffect } from "react";
import type { DeviceState } from "../../types/device";

export function useDeviceSse() {
  const queryClient = useQueryClient();

  useEffect(() => {
    const es = new EventSource("http://localhost:8080/api/stream");

    es.addEventListener("connection", () => {
      console.log("SSE connected");
    });

    es.addEventListener("device_update", (event) => {
      const data = JSON.parse((event as MessageEvent).data) as DeviceState;

      queryClient.setQueryData<DeviceState[]>(["devices"], (old) => {
        const prev = old ?? [];
        const idx = prev.findIndex((d) => d.deviceId === data.deviceId);
        if (idx === -1) return [data, ...prev];
        const copy = prev.slice();
        copy[idx] = data;
        return copy;
      });
    });

    es.addEventListener("heartbeat", () => {
      console.log("ping!!");
    });

    es.onerror = () => {
      console.log("SSE error");
    };

    return () => {
      es.close();
    };
  }, [queryClient]);
}
