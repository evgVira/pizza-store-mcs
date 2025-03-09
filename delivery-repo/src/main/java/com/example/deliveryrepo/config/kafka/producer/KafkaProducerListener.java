package com.example.deliveryrepo.config.kafka.producer;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.ProducerListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaProducerListener implements ProducerListener<String, Object> {

    @Override
    public void onSuccess(ProducerRecord<String, Object> producerRecord, RecordMetadata recordMetadata) {
        log.info("Successfully processed record: topic={}, partition={}, offset={}, key={}, value={}",
                producerRecord.topic(), recordMetadata.offset(), recordMetadata.partition(),
                producerRecord.key(), producerRecord.value());
    }

    @Override
    public void onError(ProducerRecord<String, Object> producerRecord, RecordMetadata recordMetadata, Exception exception) {
        log.error("Error processed record: topic={}, key={}, value={}, exception={}",
                producerRecord.topic(), producerRecord.key(), producerRecord.value(), exception.getMessage(), exception);
    }
}
