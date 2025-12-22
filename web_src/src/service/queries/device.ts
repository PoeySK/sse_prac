import { useQuery } from "@tanstack/react-query";
import { api } from "../api";
import type { DeviceState } from "../../types/device";

export function useFindAll() {
  return useQuery({
    queryKey: ["devices"],
    queryFn: async () => {
      const response = await api.get<DeviceState[]>("/devices");
      return response.data;
    },
  });
}
