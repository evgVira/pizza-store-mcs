package com.example.catalogrepo.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PizzaCreateRequestDto {

    @NotNull(message = "Название не указано")
    @Size(min = 5, max = 20)
    private String name;

    private String description;

    @NotNull(message = "Цена не указана")
    private BigDecimal price;

}
