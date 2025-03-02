package com.example.orderrepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class OrderRepoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderRepoApplication.class, args);
    }

}
