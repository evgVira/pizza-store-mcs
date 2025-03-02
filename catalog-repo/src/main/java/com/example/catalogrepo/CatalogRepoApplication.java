package com.example.catalogrepo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CatalogRepoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CatalogRepoApplication.class, args);
    }

}
