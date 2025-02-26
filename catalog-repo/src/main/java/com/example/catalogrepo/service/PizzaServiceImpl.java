package com.example.catalogrepo.service;

import com.example.catalogrepo.config.exception.ResourceNotFoundException;
import com.example.catalogrepo.dto.PizzaCreateRequestDto;
import com.example.catalogrepo.dto.PizzaInfoResponseDto;
import com.example.catalogrepo.mapper.PizzaMapper;
import com.example.catalogrepo.model.Pizza;
import com.example.catalogrepo.repository.PizzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PizzaServiceImpl implements PizzaService {

    private static final String PIZZA_NOT_FOUND_MESSAGE = "Pizza by id: %s not found";

    private final PizzaRepository pizzaRepository;

    private final PizzaMapper pizzaMapper;

    @Override
    @Transactional
    public PizzaInfoResponseDto createPizza(PizzaCreateRequestDto pizzaCreateRequestDto) {
        Pizza pizza = pizzaMapper.mapToPizzaEntity(pizzaCreateRequestDto);
        pizzaRepository.save(pizza);
        return pizzaMapper.mapToPizzaInfoResponseDto(pizza);
    }

    @Override
    public PizzaInfoResponseDto getPizzaById(UUID id) {
        Pizza pizza = findPizzaById(id);
        return pizzaMapper.mapToPizzaInfoResponseDto(pizza);
    }

    @Override
    public List<PizzaInfoResponseDto> getAllPizzas() {
        List<Pizza> pizzas = pizzaRepository.findAll();
        if (pizzas.isEmpty()) {
            return Collections.EMPTY_LIST;
        }
        return pizzas.stream()
                .map(pizzaMapper::mapToPizzaInfoResponseDto)
                .toList();
    }

    @Override
    @Transactional
    public void deletePizzaById(UUID id) {
        Pizza pizza = findPizzaById(id);
        pizzaRepository.delete(pizza);
    }

    private Pizza findPizzaById(UUID id) {
        return pizzaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(PIZZA_NOT_FOUND_MESSAGE, id)));
    }

}
