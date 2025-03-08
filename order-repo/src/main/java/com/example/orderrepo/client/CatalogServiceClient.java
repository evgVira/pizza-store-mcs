package com.example.orderrepo.client;

import com.example.orderrepo.dto.PizzaInfoResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "catalog-service")
public interface CatalogServiceClient {

    @GetMapping("/api/v1/catalog/pizzas/info/{pizzaId}")
    PizzaInfoResponseDto getPizzaById(@PathVariable("pizzaId") UUID pizzaId);

}
