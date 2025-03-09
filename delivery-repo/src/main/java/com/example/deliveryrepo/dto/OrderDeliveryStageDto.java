package com.example.deliveryrepo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDeliveryStageDto {

    private UUID orderId;

    private UUID userId;

    private String status;

}
