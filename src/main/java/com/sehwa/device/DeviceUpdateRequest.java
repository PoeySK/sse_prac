package com.sehwa.device;

public class DeviceUpdateRequest {
    private double value;

    public DeviceUpdateRequest() {}

    public DeviceUpdateRequest(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
