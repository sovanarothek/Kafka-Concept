package com.study.kafka.consumer;

import com.study.kafka.model.UserEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserEventConsumer {

    @KafkaListener(topics = "${kafka.topic.name}", groupId = "my-group")
    public void consume(
            @Payload UserEvent event,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset) {

        log.info("Received: {} | Partition: {} | Offset: {}", event, partition, offset);
    }
}
