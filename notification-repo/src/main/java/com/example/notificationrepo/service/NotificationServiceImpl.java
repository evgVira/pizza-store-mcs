package com.example.notificationrepo.service;

import com.example.notificationrepo.dto.Notification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationServiceImpl {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "order-topic", groupId = "order-group", containerFactory = "kafkaListenerContainerFactory")
    public void consumerNotification(@Payload String notification) {
        var readerForNotification = objectMapper.readerFor(Notification.class);
        try {
            Notification consumedNotification = readerForNotification.readValue(notification);
            log.info("Consumed notification: {}", consumedNotification);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to deserialize notification");
        }
    }
}
