package com.example.catalogrepo.service;

import com.example.catalogrepo.dto.PizzaCreateRequestDto;
import com.example.catalogrepo.dto.PizzaInfoResponseDto;

import java.util.List;
import java.util.UUID;

public interface PizzaService {

    PizzaInfoResponseDto createPizza(PizzaCreateRequestDto pizzaCreateRequestDto);

    PizzaInfoResponseDto getPizzaById(UUID id);

    List<PizzaInfoResponseDto> getAllPizzas();

    void deletePizzaById(UUID id);
}
