package com.example.catalogrepo.service;

import com.example.catalogrepo.config.exception.ResourceNotFoundException;
import com.example.catalogrepo.config.exception.UserRequestException;
import com.example.catalogrepo.dto.PizzaCreateRequestDto;
import com.example.catalogrepo.dto.PizzaInfoResponseDto;
import com.example.catalogrepo.mapper.PizzaMapper;
import com.example.catalogrepo.model.Pizza;
import com.example.catalogrepo.repository.PizzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PizzaServiceImpl implements PizzaService {

    private static final String PIZZA_NOT_FOUND = "Pizza by id: %s not found";

    private static final String PIZZA_ALREADY_EXIST = "Pizza with name: %s already exists";

    private static final String PIZZA_CACHE_KEY = "pizza:count:";

    private final PizzaRepository pizzaRepository;

    private final PizzaMapper pizzaMapper;

    private final RedisCacheService redisCacheService;

    @Override
    @Transactional
    public PizzaInfoResponseDto createPizza(PizzaCreateRequestDto pizzaCreateRequestDto) {
        findPizzaByName(pizzaCreateRequestDto.getName());
        Pizza pizza = pizzaMapper.mapToPizzaEntity(pizzaCreateRequestDto);
        pizzaRepository.save(pizza);
        return pizzaMapper.mapToPizzaInfoResponseDto(pizza);
    }

    @Override
//    @Cacheable(value = "pizzaCache", key = "#id")
    public PizzaInfoResponseDto getPizzaById(UUID id) {
        Pizza pizza = findPizzaById(id);
        return pizzaMapper.mapToPizzaInfoResponseDto(pizza);
    }

    @Override
    @Cacheable(value = "pizzaList")
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
                .orElseThrow(() -> new ResourceNotFoundException(String.format(PIZZA_NOT_FOUND, id)));
    }

    private void findPizzaByName(String name) {
        boolean pizzaExist = pizzaRepository.existsPizzaByName(name);
        if (pizzaExist) {
            throw new UserRequestException(String.format(PIZZA_ALREADY_EXIST, name));
        }
    }

}
