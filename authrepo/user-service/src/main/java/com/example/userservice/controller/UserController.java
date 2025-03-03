package com.example.userservice.controller;

import com.example.userservice.client.KeycloakClient;
import com.example.userservice.dto.request.RegisterRequestDto;
import com.example.userservice.dto.response.AccessTokenResponseDto;
import com.example.userservice.dto.response.AdminTokenResponseDto;
import com.example.userservice.dto.response.RegisterResponseInfoDto;
import com.example.userservice.dto.response.RoleResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {


    private final KeycloakClient keycloakClient;

    @PostMapping("/login/user/{username}/password/{password}")
    public AccessTokenResponseDto loginAndGetToken(@PathVariable("username") String username, @PathVariable("password") String password) {
        return keycloakClient.loginAndGetToken(username, password);
    }

    @PostMapping("/register")
    public RegisterResponseInfoDto registerAndGetToken(@RequestBody RegisterRequestDto registerRequestDto){
        return keycloakClient.registerAndGetToken(registerRequestDto);
    }

    @PostMapping("/token/admin")
    public AdminTokenResponseDto getAdminToken(){
        return keycloakClient.getAdminToken();
    }

    @GetMapping("/roles")
    public List<RoleResponseDto> getAllRoles(){
        return keycloakClient.getAllRoles();
    }

}
