package com.example.userservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterResponseInfoDto {

    private String username;

    private String password;

    private String email;

    private String accessToken;

    private String refreshToken;

}
