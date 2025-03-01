package com.example.orderrepo.mapper;

import com.example.orderrepo.dto.NotificationDto;
import com.example.orderrepo.model.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    public String mapToNotificationDtoAsString(Order order, ObjectMapper objectMapper) {
        try {
            NotificationDto notificationDto = NotificationDto.builder()
                    .orderId(order.getId())
                    .userId(order.getUserId())
                    .pizzaId(order.getPizzaId())
                    .totalAmount(order.getTotalAmount())
                    .build();
            return objectMapper.writeValueAsString(notificationDto);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
