package com.example.orderrepo.controller;

import com.example.orderrepo.dto.OrderInfoResponseDto;
import com.example.orderrepo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create/pizzas/{pizzaId}/totalAmount/{totalAmount}")
    @ResponseStatus(HttpStatus.CREATED)
    public OrderInfoResponseDto createOrder(@PathVariable("pizzaId") UUID pizzaId,
                                            @PathVariable("totalAmount") BigDecimal totalAmount, Authentication authentication) {
        return orderService.createOrder(getUserId(authentication), pizzaId, totalAmount);
    }

    @DeleteMapping("/delete/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") UUID orderId) {
        orderService.deleteOrder(orderId);
    }

    private static UUID getUserId(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userIdAsString = jwt.getClaimAsString("sub");
        return UUID.fromString(userIdAsString);
    }

}
