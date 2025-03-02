package com.example.eurekarepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaRepoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaRepoApplication.class, args);
    }

}
