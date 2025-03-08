package com.example.catalogrepo.dto;

import com.example.catalogrepo.enums.PizzaAvailableStatus;
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

    private String createdAt;

    private String updatedAt;

    private PizzaAvailableStatus status;

}
