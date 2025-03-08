package com.example.orderrepo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PizzaInfoResponseDto {

    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

    private String createdAt;

    private String updatedAt;

    private String status;

}
