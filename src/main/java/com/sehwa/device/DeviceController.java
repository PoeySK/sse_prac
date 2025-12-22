package com.sehwa.device;

import com.sehwa.sse.SseHub;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Collection;

@RestController
@RequestMapping("/api")
public class DeviceController {
    private final DeviceService deviceService;
    private final SseHub sseHub;

    public DeviceController(DeviceService deviceService, SseHub sseHub) {
        this.deviceService = deviceService;
        this.sseHub = sseHub;
    }

    @GetMapping("/stream")
    public SseEmitter stream() {
        return sseHub.subscribe();
    }

    @GetMapping("/devices")
    public Collection<DeviceState> getDevices() {
        return deviceService.findAll();
    }

    @PostMapping("/devices/{deviceId}/data")
    public DeviceState update(@PathVariable String deviceId, @RequestBody DeviceUpdateRequest req) {
        return deviceService.update(deviceId, req.getValue());
    }

    // 테스트 실행용
    @GetMapping("/test/fire")
    public DeviceState fire(@RequestParam String deviceId, @RequestParam double value) {
        return deviceService.update(deviceId, value);
    }
}
