package com.example.notificationrepo.controller;

import com.example.notificationrepo.client.OrderServiceClient;
import com.example.notificationrepo.dto.ChangeOrderStatusRequestDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    private final OrderServiceClient orderServiceClient;

    @GetMapping("/api/v1/notification/test")
    public void testChangeStatus(@RequestBody ChangeOrderStatusRequestDto changeOrderStatusRequestDto){
        orderServiceClient.changeOrderStatus(changeOrderStatusRequestDto);
        log.info("successful change order status");
    }

    @GetMapping("/api/v1/notification/str")
    public String test(){
        return "test";
    }

}
