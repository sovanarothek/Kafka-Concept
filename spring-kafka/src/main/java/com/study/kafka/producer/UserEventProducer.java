package com.study.kafka.producer;

import com.study.kafka.model.UserEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserEventProducer {

    private final KafkaTemplate<String, UserEvent> kafkaTemplate;

    @Value("${kafka.topic.name}")
    private String topicName;

    public void sendEvent(UserEvent event) {
        CompletableFuture<SendResult<String, UserEvent>> future =
                kafkaTemplate.send(topicName, String.valueOf(event.getUserId()), event);

        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent: {} | Partition: {} | Offset: {}",
                        event,
                        result.getRecordMetadata().partition(),
                        result.getRecordMetadata().offset());
            } else {
                log.error("Failed to send: {}", ex.getMessage());
            }
        });
    }
}
