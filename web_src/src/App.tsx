
import { useDeviceSse } from "./service/hooks/useDeviceSse";
import { useFindAll } from "./service/queries/device";

export default function App() {
  useDeviceSse();
  const { data, isLoading, isError } = useFindAll();

  if (isLoading) return <div style={{ padding: 16 }}>Loading...</div>;
  if (isError) return <div style={{ padding: 16 }}>Error</div>;

  return (
    <div style={{ padding: 16 }}>
      <h2>React Dashboard (SSE)</h2>
      <ul>
        {(data ?? []).map((d) => (
          <li key={d.deviceId}>
            {d.deviceId} / {d.value} / {new Date(d.updatedAt).toLocaleTimeString()}
          </li>
        ))}
      </ul>
      <p>test.html에서 버튼 누르면 여기 리스트가 즉시 갱신되어야 합니다.</p>
    </div>
  );
}
