package com.example.orderrepo.mapper;

import com.example.orderrepo.dto.OrderInfoResponseDto;
import com.example.orderrepo.model.Order;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.UUID;

import static com.example.orderrepo.enums.OrderStatuses.CREATED;

@Component
public class OrderMapper {

    public Order mapToOrder(UUID userId, UUID pizzaId, BigDecimal totalAmount) {
        return Order.builder()
                .userId(userId)
                .pizzaId(pizzaId)
                .totalAmount(totalAmount)
                .status(CREATED)
                .build();
    }

    public OrderInfoResponseDto mapToOrderInfoResponseDto(Order order) {
        return OrderInfoResponseDto.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .pizzaId(order.getPizzaId())
                .totalAmount(order.getTotalAmount())
                .statuses(order.getStatus())
                .build();
    }

}
