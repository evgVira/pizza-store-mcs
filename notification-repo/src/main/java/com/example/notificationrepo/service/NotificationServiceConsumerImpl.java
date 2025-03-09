package com.example.notificationrepo.service;

import com.example.notificationrepo.client.OrderServiceClient;
import com.example.notificationrepo.dto.ChangeOrderStatusRequestDto;
import com.example.notificationrepo.dto.OrderDeliveryStageDto;
import com.example.notificationrepo.dto.OrderNotification;
import com.example.notificationrepo.enums.OrderStatus;
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

    private static final String ORDER_DLT_TOPIC = "order-dlt-topic";

    private static final String ORDER_STAGE_TOPIC = "order-stage-topic";

    private static final String ORDER_STAGE_DLT_TOPIC = "order-stage-dlt-topic";

    private static final String ORDER_GROUP_ID = "order-group";

    private final OrderServiceClient orderServiceClient;

    @Override
    @KafkaListener(topics = {ORDER_TOPIC, ORDER_DLT_TOPIC}, groupId = ORDER_GROUP_ID, containerFactory = "kafkaListenerContainerFactoryFroOrderNotification")
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
    @KafkaListener(topics = {ORDER_STAGE_TOPIC, ORDER_STAGE_DLT_TOPIC}, groupId = ORDER_GROUP_ID, containerFactory = "kafkaListenerContainerFactoryForOrderStage")
    public void consumeStageNotification(@Payload String notification) {
        var readerForOrderDeliverStageDto = objectMapper.readerFor(OrderDeliveryStageDto.class);
        OrderDeliveryStageDto orderDeliveryStageDto;
        try {
            orderDeliveryStageDto = readerForOrderDeliverStageDto.readValue(notification);
            log.info("Consumed notification: {}", orderDeliveryStageDto);
        } catch (JsonProcessingException exception) {
            throw new RuntimeException("Failed to deserialize notification");
        }
        ChangeOrderStatusRequestDto changeOrderStatusRequestDto = new ChangeOrderStatusRequestDto(
                orderDeliveryStageDto.getOrderId(), OrderStatus.valueOf(orderDeliveryStageDto.getStatus())
        );
        orderServiceClient.changeOrderStatus(changeOrderStatusRequestDto);
    }
}
