package com.example.deliveryrepo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderNotification {

    private UUID orderId;

    private UUID userId;

    private UUID pizzaId;

    private BigDecimal totalAmount;

    private String status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
