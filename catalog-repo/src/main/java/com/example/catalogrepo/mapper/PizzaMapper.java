package com.example.catalogrepo.mapper;

import com.example.catalogrepo.dto.PizzaCreateRequestDto;
import com.example.catalogrepo.dto.PizzaInfoResponseDto;
import com.example.catalogrepo.model.Pizza;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

import static com.example.catalogrepo.enums.PizzaAvailableStatus.AVAILABLE;

@Component
public class PizzaMapper {

    public Pizza mapToPizzaEntity(PizzaCreateRequestDto pizzaCreateRequestDto) {
        return Pizza.builder()
                .name(pizzaCreateRequestDto.getName())
                .description(pizzaCreateRequestDto.getDescription())
                .price(pizzaCreateRequestDto.getPrice())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .status(AVAILABLE)
                .build();

    }

    public PizzaInfoResponseDto mapToPizzaInfoResponseDto(Pizza pizza) {
        return PizzaInfoResponseDto.builder()
                .id(pizza.getId())
                .name(pizza.getName())
                .description(pizza.getDescription())
                .price(pizza.getPrice())
                .createdAt(String.valueOf(pizza.getCreatedAt()))
                .updatedAt(String.valueOf(pizza.getUpdatedAt()))
                .status(pizza.getStatus())
                .build();
    }
}
