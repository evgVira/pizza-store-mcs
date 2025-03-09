package com.example.notificationrepo.service;

public interface NotificationServiceConsumer {

    void consumeNotification(String notification);

    void consumeStageNotification(String notification);

}
