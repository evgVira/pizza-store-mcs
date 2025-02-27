package com.example.orderrepo.service;

import com.example.orderrepo.dto.OrderInfoResponseDto;

import java.math.BigDecimal;
import java.util.UUID;

public interface OrderService {

    OrderInfoResponseDto createOrder(UUID userId, UUID pizzaId, BigDecimal totalAmount);

    void deleteOrder(UUID orderId);
}
