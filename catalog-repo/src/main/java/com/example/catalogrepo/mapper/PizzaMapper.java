package com.example.catalogrepo.mapper;

import com.example.catalogrepo.dto.PizzaCreateRequestDto;
import com.example.catalogrepo.dto.PizzaInfoResponseDto;
import com.example.catalogrepo.model.Pizza;
import org.springframework.stereotype.Component;

@Component
public class PizzaMapper {

    public Pizza mapToPizzaEntity(PizzaCreateRequestDto pizzaCreateRequestDto) {
        return Pizza.builder()
                .name(pizzaCreateRequestDto.getName())
                .description(pizzaCreateRequestDto.getDescription())
                .price(pizzaCreateRequestDto.getPrice())
                .build();

    }

    public PizzaInfoResponseDto mapToPizzaInfoResponseDto(Pizza pizza) {
        return PizzaInfoResponseDto.builder()
                .id(pizza.getId())
                .name(pizza.getName())
                .description(pizza.getDescription())
                .price(pizza.getPrice())
                .build();
    }
}
