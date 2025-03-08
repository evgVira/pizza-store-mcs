package com.example.orderrepo.controller;

import com.example.orderrepo.dto.ChangeOrderStatusRequestDto;
import com.example.orderrepo.dto.OrderInfoResponseDto;
import com.example.orderrepo.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
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
        UUID userId = orderService.getUserId(authentication);
        return orderService.createOrder(userId, pizzaId, totalAmount);
    }

    @DeleteMapping("/delete/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") UUID orderId) {
        orderService.deleteOrder(orderId);
    }

    @PutMapping("/status/change")
    @ResponseStatus(HttpStatus.OK)
    public void changeOrderStatus(@RequestBody ChangeOrderStatusRequestDto changeOrderStatusRequestDto) {
        orderService.changeOrderStatus(changeOrderStatusRequestDto);
    }

}
