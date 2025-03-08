package com.example.deliveryrepo.service;

import com.example.deliveryrepo.dto.OrderNotification;
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
public class NotificationServiceConsumerImpl implements NotificationServiceConsumer{

    private final ObjectMapper objectMapper;

    private static final String ORDER_TOPIC = "order-topic";

    private static final String ORDER_DLT_TOPIC =  "order-dlt-topic";

    private static final String  ORDER_GROUP_ID = "delivery-group";

    private static final String DLT_TOPIC_CONSUME = "Consume message from DLT topic: {}";


    @Override
    @KafkaListener(topics = ORDER_TOPIC, groupId = ORDER_GROUP_ID, containerFactory = "kafkaListenerContainerFactory")
    public void consumeNotification(@Payload String notification) {
        var readerForNotification = objectMapper.readerFor(OrderNotification.class);
        try{
            OrderNotification consumeOrderNotification = readerForNotification.readValue(notification);
            log.info("Consumed notification: {}", consumeOrderNotification);
        }catch (JsonProcessingException exception){
            throw new RuntimeException("Failed to deserialize notification");
        }
    }

    @Override
    @KafkaListener(topics = ORDER_DLT_TOPIC, groupId = ORDER_GROUP_ID, containerFactory = "kafkaListenerContainerFactoryDlt")
    public void consumeNotificationFromDlt(@Payload String notification){
        log.info(DLT_TOPIC_CONSUME, notification);
        var readerForNotification = objectMapper.readerFor(OrderNotification.class);
        try{
            OrderNotification consumeOrderNotification = readerForNotification.readValue(notification);
            log.info("Consumed notification: {}", consumeOrderNotification);
        }catch (JsonProcessingException exception){
            throw new RuntimeException("Failed to deserialize notification");
        }
    }
}
