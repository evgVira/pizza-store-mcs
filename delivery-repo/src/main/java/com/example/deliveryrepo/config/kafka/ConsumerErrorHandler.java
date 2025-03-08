package com.example.deliveryrepo.config.kafka;

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

    private static final String DELIVER_DLT_TOPIC = "delivery-dlt-topic";

    private final KafkaTemplate<String, String> kafkaTemplate;

    public DefaultErrorHandler errorHandler() {
        DeadLetterPublishingRecoverer recoverer = new DeadLetterPublishingRecoverer(
                kafkaTemplate,
                (((consumerRecord, e) -> new TopicPartition(DELIVER_DLT_TOPIC, consumerRecord.partition())))
        );
        return new DefaultErrorHandler(recoverer, new FixedBackOff(2000L, 3));
    }

}
