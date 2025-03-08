package com.example.orderrepo.mapper;

import com.example.orderrepo.dto.OrderInfoResponseDto;
import com.example.orderrepo.model.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static com.example.orderrepo.enums.OrderStatus.CREATED;

@Component
public class OrderMapper {

    public Order mapToOrder(UUID userId, UUID pizzaId, BigDecimal totalAmount) {
        return Order.builder()
                .userId(userId)
                .pizzaId(pizzaId)
                .totalAmount(totalAmount)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(CREATED)
                .build();
    }

    public OrderInfoResponseDto mapToOrderInfoResponseDto(Order order) {
        return OrderInfoResponseDto.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .pizzaId(order.getPizzaId())
                .totalAmount(order.getTotalAmount())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .updatedAt(order.getUpdatedAt())
                .build();
    }

}
