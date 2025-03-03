package com.example.userservice.mapper;

import com.example.userservice.dto.request.CreateUserDto;
import com.example.userservice.dto.request.CredentialsDto;
import com.example.userservice.dto.request.RegisterRequestDto;
import com.example.userservice.dto.response.RegisterResponseInfoDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CreateUserMapper {

    private static final String CRED_TYPE = "password";

    private static final String LAST_NAME = "none";

    public CreateUserDto mapToCreateUserDto(RegisterRequestDto registerRequestDto) {
        return CreateUserDto.builder()
                .username(registerRequestDto.getUsername())
                .enabled(true)
                .firstName(registerRequestDto.getUsername())
                .lastName(LAST_NAME)
                .email(registerRequestDto.getEmail())
                .emailVerified(true)
                .credentials(List.of(mapToCredentialsDto(registerRequestDto.getPassword())))
                .build();
    }

    public RegisterResponseInfoDto mapToRegisterResponseInfoDto(String username, String password, String accessToken){
        return RegisterResponseInfoDto.builder()
                .username(username)
                .password(password)
                .accessToken(accessToken)
                .build();
    }

    private static CredentialsDto mapToCredentialsDto(String password) {
        return CredentialsDto.builder()
                .type(CRED_TYPE)
                .value(password)
                .temporary(false)
                .build();

    }
}
