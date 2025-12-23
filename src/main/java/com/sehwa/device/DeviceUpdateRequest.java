package com.sehwa.device;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeviceUpdateRequest {
    private double value;

    public DeviceUpdateRequest() {}

    public DeviceUpdateRequest(double value) {
        this.value = value;
    }
}
