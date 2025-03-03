package com.example.userservice.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoleResponseDto {

    private UUID id;

    private String name;

    private Boolean composite;

    private Boolean clientRole;

    private UUID containerId;
}
