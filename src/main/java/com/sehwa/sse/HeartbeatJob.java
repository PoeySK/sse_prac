package com.sehwa.sse;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HeartbeatJob {
    private final SseHub sseHub;

    public HeartbeatJob(SseHub sseHub) {
        this.sseHub = sseHub;
    }

    @Scheduled(fixedRate = 5000)
    public void ping() {
        sseHub.heartbeat();
    }
}
