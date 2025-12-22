package com.sehwa.sse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class SseHub {
    private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();

    // 브라우저가 SSE 구독 시 호출
    public SseEmitter subscribe() {
        SseEmitter emitter = new SseEmitter(60L * 60L * 1000L);
        String id = UUID.randomUUID().toString();
        emitters.put(id, emitter);

        emitter.onCompletion(() -> emitters.remove(id));
        emitter.onTimeout(() -> emitters.remove(id));
        emitter.onError((e) -> emitters.remove(id));

        try {
            emitter.send(SseEmitter.event()
                    .name("connected")
                    .id(String.valueOf(Instant.now().toEpochMilli()))
                    .data("ok"));
        } catch (IOException e) {
            emitters.remove(id);
            System.err.println("구독 오류:\n" + e);
        }

        return emitter;
    }

    public void broadcast(String eventName, String eventId, Object data) {
        for (Map.Entry<String, SseEmitter> entry : emitters.entrySet()) {
            String key = entry.getKey();
            SseEmitter emitter = entry.getValue();
            try {
                emitter.send(SseEmitter.event()
                        .name(eventName)
                        .id(eventId)
                        .data(data));
            } catch (IOException e) {
                emitters.remove(key);
                System.err.println("브로드캐스트 오류:\n" + e);
            }
        }
    }

    public void heartbeat() {
        broadcast("heartbeat", String.valueOf(Instant.now().toEpochMilli()), "ping");
    }
}
