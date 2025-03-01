package com.example.notificationrepo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Notification {

    private UUID orderId;

    private UUID userId;

    private UUID pizzaId;

    private BigDecimal totalAmount;

}
