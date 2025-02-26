package com.example.catalogrepo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PizzaInfoResponseDto {

    private UUID id;

    private String name;

    private String description;

    private BigDecimal price;

}
