package com.example.notificationrepo.service;

import com.example.notificationrepo.dto.OrderNotification;
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

    //todo реализовать логику отправки уведомлений пользователю через WebSocket

    private final ObjectMapper objectMapper;

    private static final String ORDER_TOPIC = "order-topic";

    private static final String ORDER_DLT_TOPIC = "order-dlt-topic";

    private static final String ORDER_GROUP_ID = "order-group";

    private static final String DELIVER_DLT_TOPIC = "delivery-dlt-topic";

    @Override
    @KafkaListener(topics = {ORDER_TOPIC}, groupId = ORDER_GROUP_ID, containerFactory = "kafkaListenerContainerFactory")
    public void consumeNotification(@Payload String notification) {
        var readerForNotification = objectMapper.readerFor(OrderNotification.class);
        try {
            OrderNotification consumedOrderNotification = readerForNotification.readValue(notification);
            log.info("Consumed notification: {}", consumedOrderNotification);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize notification");
        }
    }

    @Override
    @KafkaListener(topics = {ORDER_DLT_TOPIC}, groupId = ORDER_GROUP_ID, containerFactory = "kafkaListenerContainerFactory")
    public void consumeNotificationFromDlt(@Payload String notification) {
        var readerForNotification = objectMapper.readerFor(OrderNotification.class);
        try {
            OrderNotification consumedOrderNotification = readerForNotification.readValue(notification);
            log.info("Consumed notification From {} topic: {}", ORDER_DLT_TOPIC, consumedOrderNotification);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize notification");
        }
    }

    @KafkaListener(topics = {DELIVER_DLT_TOPIC}, groupId = ORDER_GROUP_ID, containerFactory = "kafkaListenerContainerFactory")
    public void consumeFromDeliveryDltTopic(@Payload String notification) {
        var readerForNotification = objectMapper.readerFor(OrderNotification.class);
        try {
            OrderNotification consumedOrderNotification = readerForNotification.readValue(notification);
            log.info("Consumed notification From {} topic: {}", DELIVER_DLT_TOPIC, consumedOrderNotification);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize notification");
        }
    }

}
