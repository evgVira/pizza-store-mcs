package com.example.deliveryrepo.service.impl;

import com.example.deliveryrepo.dto.OrderNotification;
import com.example.deliveryrepo.service.DeliveryService;
import com.example.deliveryrepo.service.NotificationServiceConsumer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceConsumerImpl implements NotificationServiceConsumer {

    private final ObjectMapper objectMapper;

    private static final String ORDER_TOPIC = "order-topic";

    private static final String ORDER_GROUP_ID = "delivery-group";

    private final DeliveryService deliveryService;


    @Override
    @KafkaListener(topics = {ORDER_TOPIC}, groupId = ORDER_GROUP_ID, containerFactory = "kafkaListenerContainerFactory")
    public void consumeNotification(@Payload String notification) {
        var readerForNotification = objectMapper.readerFor(OrderNotification.class);
        OrderNotification consumeOrderNotification;
        try {
            consumeOrderNotification = readerForNotification.readValue(notification);
            log.info("Consumed notification: {}", consumeOrderNotification);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException("Failed to deserialize notification");
        }
        deliveryService.createPizzaAndDeliveryOrder(consumeOrderNotification);
    }

}
