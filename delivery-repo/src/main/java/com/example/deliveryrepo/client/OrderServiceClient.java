package com.example.deliveryrepo.client;

import com.example.deliveryrepo.dto.ChangeOrderStatusRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "order-service")
public interface OrderServiceClient {

    @PutMapping("/api/v1/order/status/change")
    void changeOrderStatus(@RequestBody ChangeOrderStatusRequestDto changeOrderStatusRequestDto);
}
