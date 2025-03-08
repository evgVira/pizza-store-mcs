package com.example.orderrepo.service;

import com.example.orderrepo.dto.ChangeOrderStatusRequestDto;
import com.example.orderrepo.dto.OrderInfoResponseDto;
import org.springframework.security.core.Authentication;

import java.math.BigDecimal;
import java.util.UUID;

public interface OrderService {

    OrderInfoResponseDto createOrder(UUID userId, UUID pizzaId, BigDecimal totalAmount);

    void deleteOrder(UUID orderId);

    UUID getUserId(Authentication authentication);

    void changeOrderStatus(ChangeOrderStatusRequestDto changeOrderStatusRequestDto);
}
