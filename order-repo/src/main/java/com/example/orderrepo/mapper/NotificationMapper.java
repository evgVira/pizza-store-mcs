package com.example.orderrepo.mapper;

import com.example.orderrepo.dto.OrderNotificationDto;
import com.example.orderrepo.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public String mapToNotificationDtoAsString(Order order, ObjectMapper objectMapper) {
        try {
            OrderNotificationDto orderNotificationDto = OrderNotificationDto.builder()
                    .orderId(order.getId())
                    .userId(order.getUserId())
                    .pizzaId(order.getPizzaId())
                    .totalAmount(order.getTotalAmount())
                    .status(order.getStatus().toString())
                    .createdAt(order.getCreatedAt())
                    .updatedAt(order.getUpdatedAt())
                    .build();
            return objectMapper.writeValueAsString(orderNotificationDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
