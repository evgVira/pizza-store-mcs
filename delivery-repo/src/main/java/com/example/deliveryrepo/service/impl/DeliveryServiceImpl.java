package com.example.deliveryrepo.service.impl;

import com.example.deliveryrepo.client.OrderServiceClient;
import com.example.deliveryrepo.dto.ChangeOrderStatusRequestDto;
import com.example.deliveryrepo.dto.OrderNotification;
import com.example.deliveryrepo.enums.OrderStatus;
import com.example.deliveryrepo.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.example.deliveryrepo.enums.OrderStatus.*;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private static final int WAIT_TIME = 60000;

    private final OrderServiceClient orderServiceClient;

    @Override
    public void createPizzaAndDeliveryOrder(OrderNotification orderNotification) {
        OrderStatus orderStatus = OrderStatus.valueOf(orderNotification.getStatus());
        try {
            if (orderStatus == CREATED) {
                UUID orderId = orderNotification.getOrderId();
                waitOneMinuteAndChangeStatus(orderId, COOKING);
                waitOneMinuteAndChangeStatus(orderId, DELIVERY);
                waitOneMinuteAndChangeStatus(orderId, COMPLETED);
            }
        } catch (InterruptedException exception) {
            orderServiceClient.changeOrderStatus(new ChangeOrderStatusRequestDto(orderNotification.getOrderId(), CANCELED));
            Thread.currentThread().interrupt();
        }
    }

    private void waitOneMinuteAndChangeStatus(UUID orderId, OrderStatus orderStatus) throws InterruptedException {
        Thread.sleep(WAIT_TIME);
        orderServiceClient.changeOrderStatus(new ChangeOrderStatusRequestDto(orderId, orderStatus));
    }
}
