package com.example.deliveryrepo.service.impl;

import com.example.deliveryrepo.dto.OrderDeliveryStageDto;
import com.example.deliveryrepo.dto.OrderNotification;
import com.example.deliveryrepo.enums.OrderStatus;
import com.example.deliveryrepo.mapper.OrderDeliveryStageMapper;
import com.example.deliveryrepo.service.DeliveryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.deliveryrepo.enums.OrderStatus.*;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final ObjectMapper objectMapper;

    private final OrderDeliveryStageMapper orderDeliveryStageMapper;


    @Override
    public String createPizzaAndDeliveryOrder(OrderNotification orderNotification) {
        OrderStatus orderStatus = OrderStatus.valueOf(orderNotification.getStatus());
        return switch (orderStatus) {
            case CREATED -> cookingStep(orderNotification);
            case COOKING -> deliveryStep(orderNotification);
            case DELIVERY -> completedStep(orderNotification);
            case COMPLETED -> doneOrder(orderNotification);
            case CANCELED -> canceledOrder(orderNotification);
        };
    }

    private String cookingStep(OrderNotification orderNotification) {
        OrderDeliveryStageDto orderDeliveryStageDto = orderDeliveryStageMapper
                .mapToOrderDeliveryStage(orderNotification, COOKING);
        return orderDeliveryStageMapper.mapToOrderDeliverStageDtoAsString(orderDeliveryStageDto, objectMapper);
    }

    private String deliveryStep(OrderNotification orderNotification) {
        OrderDeliveryStageDto orderDeliveryStageDto = orderDeliveryStageMapper
                .mapToOrderDeliveryStage(orderNotification, DELIVERY);
        return orderDeliveryStageMapper.mapToOrderDeliverStageDtoAsString(orderDeliveryStageDto, objectMapper);
    }

    private String completedStep(OrderNotification orderNotification) {
        OrderDeliveryStageDto orderDeliveryStageDto = orderDeliveryStageMapper
                .mapToOrderDeliveryStage(orderNotification, COMPLETED);
        return orderDeliveryStageMapper.mapToOrderDeliverStageDtoAsString(orderDeliveryStageDto, objectMapper);
    }

    private String doneOrder(OrderNotification orderNotification) {
        OrderDeliveryStageDto orderDeliveryStageDto = orderDeliveryStageMapper
                .mapToOrderDeliveryStage(orderNotification, null);
        return orderDeliveryStageMapper.mapToOrderDeliverStageDtoAsString(orderDeliveryStageDto, objectMapper);
    }

    private String canceledOrder(OrderNotification orderNotification) {
        OrderDeliveryStageDto orderDeliveryStageDto = orderDeliveryStageMapper
                .mapToOrderDeliveryStage(orderNotification, null);
        return orderDeliveryStageMapper.mapToOrderDeliverStageDtoAsString(orderDeliveryStageDto, objectMapper);
    }
}
