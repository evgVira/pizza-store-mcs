package com.example.notificationrepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NotificationRepoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NotificationRepoApplication.class, args);
    }

}
