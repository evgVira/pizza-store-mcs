package com.example.apigateway.contorller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class FallbackController {

    private static final String SERVER_ERROR_MESSAGE = "Server is not available, please try later!";

    @RequestMapping(value = "/catalog", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
            RequestMethod.PATCH, RequestMethod.DELETE})
    public String catalogFallback() {
        return SERVER_ERROR_MESSAGE;
    }

    @RequestMapping(value = "/order", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
            RequestMethod.PATCH, RequestMethod.DELETE})
    public String orderFallback() {
        return SERVER_ERROR_MESSAGE;
    }

    @RequestMapping(value = "/user", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
            RequestMethod.PATCH, RequestMethod.DELETE})
    public String userFallback() {
        return SERVER_ERROR_MESSAGE;
    }

}
