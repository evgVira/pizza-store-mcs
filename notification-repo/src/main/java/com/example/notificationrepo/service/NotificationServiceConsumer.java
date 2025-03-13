package com.example.notificationrepo.service;

public interface NotificationServiceConsumer {

    void consumeNotification(String notification);

    void consumeNotificationFromDlt(String notification);

    void consumeFromDeliveryDltTopic(String notification);

}
