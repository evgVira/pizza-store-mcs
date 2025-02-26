package com.example.catalogrepo.controller;

import com.example.catalogrepo.dto.PizzaCreateRequestDto;
import com.example.catalogrepo.dto.PizzaInfoResponseDto;
import com.example.catalogrepo.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/catalog")
@RequiredArgsConstructor
public class PizzaController {

    private final PizzaService pizzaService;

    @GetMapping("/pizzas/{pizzaId}/info")
    @ResponseStatus(HttpStatus.OK)
    public PizzaInfoResponseDto getPizzaById(@PathVariable("pizzaId")UUID pizzaId){
        return pizzaService.getPizzaById(pizzaId);
    }

    @GetMapping("/pizzas/info")
    @ResponseStatus(HttpStatus.OK)
    public List<PizzaInfoResponseDto> getAllPizzas(){
        return pizzaService.getAllPizzas();
    }

    @PostMapping("/pizzas/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PizzaInfoResponseDto createPizza(@RequestBody PizzaCreateRequestDto pizzaCreateRequestDto){
        return pizzaService.createPizza(pizzaCreateRequestDto);
    }

    @DeleteMapping("/pizzas/{pizzaId}/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePizzaById(@PathVariable("pizzaId") UUID pizzaId){
        pizzaService.deletePizzaById(pizzaId);
    }
}
