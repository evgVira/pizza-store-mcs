package com.example.orderrepo.service;

import com.example.orderrepo.config.exception.ResourceNotFoundException;
import com.example.orderrepo.dto.OrderInfoResponseDto;
import com.example.orderrepo.mapper.OrderMapper;
import com.example.orderrepo.model.Order;
import com.example.orderrepo.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private static final String ORDER_NOT_FOUND_MESSAGE = "Order by id: %s not found";

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    @Override
    @Transactional
    public OrderInfoResponseDto createOrder(UUID userId, UUID pizzaId, BigDecimal totalAmount) {
        Order order = orderMapper.mapToOrder(userId, pizzaId, totalAmount);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.mapToOrderInfoResponseDto(savedOrder);
    }

    @Override
    public void deleteOrder(UUID orderId) {
        Order order = findOrderById(orderId);
        orderRepository.delete(order);
    }

    private Order findOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ORDER_NOT_FOUND_MESSAGE, orderId)));
    }
}
