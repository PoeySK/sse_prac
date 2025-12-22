package com.sehwa.device;

import com.sehwa.sse.SseHub;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class DeviceService {
    private final Map<String, DeviceState> states = new ConcurrentHashMap<>();
    private final SseHub sseHub;

    public DeviceService(SseHub sseHub) {
        this.sseHub = sseHub;
    }

    public Collection<DeviceState> findAll() {
        return states.values();
    }

    public DeviceState update(String deviceId, double value) {
        DeviceState newState = new DeviceState(deviceId, value, Instant.now());
        states.put(deviceId, newState);

        String eventId = String.valueOf(newState.getUpdateAt().toEpochMilli());
        sseHub.broadcast("device_update", eventId, newState);

        return newState;
    }
}
