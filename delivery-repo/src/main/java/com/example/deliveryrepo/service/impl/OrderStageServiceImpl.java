package com.example.deliveryrepo.service.impl;

import com.example.deliveryrepo.config.kafka.producer.KafkaProducerListener;
import com.example.deliveryrepo.service.OrderStageService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderStageServiceImpl implements OrderStageService {

    private static final String ORDER_STAGE_TOPIC = "order-stage-topic";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final RetryTemplate retryTemplate;

    private final KafkaProducerListener kafkaProducerListener;

    @Override
    public void updateOrderStatus(String orderDeliveryStageDtoAsString) {
        kafkaTemplate.setProducerListener(kafkaProducerListener);
        retryTemplate.execute(retryContext -> kafkaTemplate.send(ORDER_STAGE_TOPIC,  orderDeliveryStageDtoAsString));
    }

}
