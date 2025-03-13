package com.example.deliveryrepo.service;

import com.example.deliveryrepo.dto.OrderNotification;

public interface DeliveryService {

    void createPizzaAndDeliveryOrder(OrderNotification orderNotification);
}
