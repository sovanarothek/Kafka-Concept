package com.study.kafka.controller;

import com.study.kafka.model.UserEvent;
import com.study.kafka.producer.UserEventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/events")
@RequiredArgsConstructor
public class EventController {

    private final UserEventProducer producer;

    @PostMapping
    public ResponseEntity<String> sendEvent(@RequestBody UserEvent event) {
        event.setTimestamp(LocalDateTime.now().toString());
        producer.sendEvent(event);
        return ResponseEntity.ok("Event sent: " + event.getEventType());
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        UserEvent event = new UserEvent(1L, "USER_SIGNUP", LocalDateTime.now().toString());
        producer.sendEvent(event);
        return ResponseEntity.ok("Test event sent!");
    }
}
