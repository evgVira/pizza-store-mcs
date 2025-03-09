package com.example.notificationrepo.config.kafka;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.TopicPartition;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.backoff.FixedBackOff;

@Component
@RequiredArgsConstructor
public class ConsumerErrorHandler {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public DefaultErrorHandler errorHandler(String dltTopicName) {
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(
                kafkaTemplate,
                ((consumerRecord, e) -> new TopicPartition(dltTopicName, consumerRecord.partition()))
        );
        return new DefaultErrorHandler(recoverer, new FixedBackOff(2000L, 3));
    }
}
