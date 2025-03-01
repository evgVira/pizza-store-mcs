package com.example.orderrepo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class NotificationDto implements Serializable {

    private UUID orderId;

    private UUID userId;

    private UUID pizzaId;

    private BigDecimal totalAmount;

}
