package com.example.deliveryrepo.service;

public interface NotificationServiceConsumer {

    void consumeNotification(String notification);

    void consumeNotificationFromDlt(String notification);
}
