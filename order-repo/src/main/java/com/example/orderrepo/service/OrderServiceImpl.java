package com.example.orderrepo.service;

import com.example.orderrepo.client.CatalogServiceClient;
import com.example.orderrepo.config.exception.ResourceNotFoundException;
import com.example.orderrepo.config.exception.UserRequestException;
import com.example.orderrepo.config.kafka.KafkaProducerListener;
import com.example.orderrepo.dto.ChangeOrderStatusRequestDto;
import com.example.orderrepo.dto.OrderInfoResponseDto;
import com.example.orderrepo.dto.PizzaInfoResponseDto;
import com.example.orderrepo.enums.OrderStatus;
import com.example.orderrepo.mapper.NotificationMapper;
import com.example.orderrepo.mapper.OrderMapper;
import com.example.orderrepo.model.Order;
import com.example.orderrepo.repository.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private static final String ORDER_NOT_FOUND = "Order by id: %s not found";

    private static final String PIZZA_NOT_FOUND = "Pizza with id %s not found";

    private static final String PIZZA_NOT_AVAILABLE = "Can't create order because pizza with id %s is not available";

    private static final String ORDER_MAIN_TOPIC = "order-topic";

    private static final String PIZZA_AVAILABLE_STATUS = "AVAILABLE";

    private final OrderRepository orderRepository;

    private final OrderMapper orderMapper;

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private final RetryTemplate retryTemplate;

    private final NotificationMapper notificationMapper;

    private final ObjectMapper objectMapper;

    private final KafkaProducerListener kafkaProducerListener;

    private final CatalogServiceClient catalogServiceClient;

    @Override
    @Transactional
    public OrderInfoResponseDto createOrder(UUID userId, UUID pizzaId, BigDecimal totalAmount) {
        PizzaInfoResponseDto pizzaInfoResponseDto = checkCreatePizza(pizzaId);
        if (pizzaInfoResponseDto.getStatus().equals(PIZZA_AVAILABLE_STATUS)) {
            Order order = orderMapper.mapToOrder(userId, pizzaId, totalAmount);
            Order savedOrder = orderRepository.save(order);
            sendNotification(order);
            return orderMapper.mapToOrderInfoResponseDto(savedOrder);
        }
        throw new UserRequestException(String.format(PIZZA_NOT_AVAILABLE, pizzaId));
    }

    @Override
    @Transactional
    public void deleteOrder(UUID orderId) {
        Order order = findOrderById(orderId);
        orderRepository.delete(order);
    }

    @Override
    public UUID getUserId(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        String userIdAsString = jwt.getClaimAsString("sub");
        return UUID.fromString(userIdAsString);
    }

    @Override
    @Transactional
    public void changeOrderStatus(ChangeOrderStatusRequestDto changeOrderStatusRequestDto) {
        orderRepository.changeOrderStatus(changeOrderStatusRequestDto.getOrderId(),
                changeOrderStatusRequestDto.getStatus());
    }

    private Order findOrderById(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(String.format(ORDER_NOT_FOUND, orderId)));
    }

    private void sendNotification(Order order) {
        kafkaTemplate.setProducerListener(kafkaProducerListener);
        retryTemplate.execute(retryContext -> kafkaTemplate.send(ORDER_MAIN_TOPIC,
                notificationMapper.mapToNotificationDtoAsString(order, objectMapper)));
    }

    private PizzaInfoResponseDto checkCreatePizza(UUID pizzaId) {
        try {
            return catalogServiceClient.getPizzaById(pizzaId);
        } catch (FeignException exception) {
            throw new UserRequestException(String.format(PIZZA_NOT_FOUND, pizzaId));
        }
    }

}
