package com.example.userservice.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateUserDto {

    private String username;

    private Boolean enabled;

    private String firstName;

    private String lastName;

    private String email;

    private Boolean emailVerified;

    private List<CredentialsDto> credentials;

}
