package com.example.catalogrepo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PizzaCreateRequestDto {

    private String name;

    private String description;

    private BigDecimal price;

}
