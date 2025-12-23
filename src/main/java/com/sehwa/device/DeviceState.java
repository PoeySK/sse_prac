package com.sehwa.device;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class DeviceState {
    private final String deviceId;
    private final double value;
    private final Instant updateAt;

    public DeviceState(String deviceId, double value, Instant updateAt) {
        this.deviceId = deviceId;
        this.value = value;
        this.updateAt = updateAt;
    }
}
