package com.example.deliveryrepo.dto;

import com.example.deliveryrepo.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChangeOrderStatusRequestDto {

    private UUID orderId;

    private OrderStatus status;
}
