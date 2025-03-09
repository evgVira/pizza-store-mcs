package com.example.deliveryrepo.mapper;

import com.example.deliveryrepo.dto.OrderDeliveryStageDto;
import com.example.deliveryrepo.dto.OrderNotification;
import com.example.deliveryrepo.enums.OrderStatus;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class OrderDeliveryStageMapper {

    public OrderDeliveryStageDto mapToOrderDeliveryStage(OrderNotification orderNotification, OrderStatus orderStatus) {
        return OrderDeliveryStageDto.builder()
                .orderId(orderNotification.getOrderId())
                .userId(orderNotification.getUserId())
                .status(orderStatus.name())
                .build();
    }

    public String mapToOrderDeliverStageDtoAsString(OrderDeliveryStageDto orderDeliveryStageDto, ObjectMapper objectMapper){
        try{
            return objectMapper.writeValueAsString(orderDeliveryStageDto);
        }catch (JsonProcessingException exception){
            throw new RuntimeException("Failed to convert Dto to string", exception);
        }
    }
}
