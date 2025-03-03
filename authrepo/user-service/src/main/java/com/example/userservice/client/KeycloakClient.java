package com.example.userservice.client;

import com.example.userservice.dto.request.CreateUserDto;
import com.example.userservice.dto.request.RegisterRequestDto;
import com.example.userservice.dto.request.UserRoleAssignDto;
import com.example.userservice.dto.response.*;
import com.example.userservice.mapper.CreateUserMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

@Component
@RequiredArgsConstructor
public class KeycloakClient {

    private static final String CLIENT_ID = "pizza-client";

    private static final String ADMIN_CLIENT_ID = "admin-cli";

    private static final String ADMIN_USERNAME = "admin";

    private static final String ADMIN_PASSWORD = "admin";

    private static final String GRANT_TYPE = "password";

    private static final String USER_ROLE_NAME = "USER";

    private static final String CLIENT_SECRET = "NxXhSQNNnWvaV6LLoM3dQOVd9NBNNm1S";

    private static final String ACCESS_TOKEN_URL = "http://localhost:8088/realms/pizza-realm/protocol/openid-connect/token";

    private static final String ADMIN_TOKEN_URL = "http://localhost:8088/realms/master/protocol/openid-connect/token";

    private static final String REGISTER_USER_URL = "http://localhost:8088/admin/realms/pizza-realm/users";

    private static final String ROLE_URL = "http://localhost:8088/admin/realms/pizza-realm/roles";

    private static final String USER_ID_URL = "http://localhost:8088/admin/realms/pizza-realm/users?username=%s";

    private static final String ASSIGN_ROLE = "http://localhost:8088/admin/realms/pizza-realm/users/%s/role-mappings/realm";

    private final RestTemplate restTemplate;

    private final CreateUserMapper createUserMapper;

    private final ObjectMapper objectMapper;

    public AccessTokenResponseDto loginAndGetToken(String username, String password) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("client_id", CLIENT_ID);
        requestBody.put("grant_type", GRANT_TYPE);
        requestBody.put("username", username);
        requestBody.put("password", password);
        requestBody.put("client_secret", CLIENT_SECRET);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String requestBodyAsString = getRequestBodyAsString(requestBody);
        HttpEntity<String> request = new HttpEntity<>(requestBodyAsString, headers);

        ResponseEntity<Map> response = restTemplate.exchange(ACCESS_TOKEN_URL, HttpMethod.POST, request, Map.class);

        String accessToken = response.getBody() != null ? (String) response.getBody().get("access_token") : null;
        String refreshToken = response.getBody() != null ? (String) response.getBody().get("refresh_token") : null;
        return new AccessTokenResponseDto(accessToken, refreshToken);
    }


    public AdminTokenResponseDto getAdminToken() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("client_id", ADMIN_CLIENT_ID);
        requestBody.put("grant_type", GRANT_TYPE);
        requestBody.put("username", ADMIN_USERNAME);
        requestBody.put("password", ADMIN_PASSWORD);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String requestBodyAsString = getRequestBodyAsString(requestBody);
        HttpEntity<String> request = new HttpEntity<>(requestBodyAsString, headers);

        ResponseEntity<Map> response = restTemplate.exchange(ADMIN_TOKEN_URL, HttpMethod.POST, request, Map.class);

        String adminToken = response.getBody() != null ? (String) response.getBody().get("access_token") : null;
        return new AdminTokenResponseDto(adminToken);
    }

    public RegisterResponseInfoDto registerAndGetToken(RegisterRequestDto registerRequestDto) {
        AdminTokenResponseDto adminTokenResponseDto = getAdminToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(adminTokenResponseDto.getAdminToken());

        CreateUserDto createUserDto = createUserMapper.mapToCreateUserDto(registerRequestDto);

        HttpEntity<CreateUserDto> request = new HttpEntity<>(createUserDto, headers);

        restTemplate.exchange(REGISTER_USER_URL, HttpMethod.POST, request, String.class);

        AccessTokenResponseDto accessTokenResponseDto = loginAndGetToken(registerRequestDto.getUsername(),
                registerRequestDto.getPassword());

        assignUserRole(registerRequestDto.getUsername());

        return new RegisterResponseInfoDto(registerRequestDto.getUsername(), registerRequestDto.getPassword(),
                registerRequestDto.getEmail(), accessTokenResponseDto.getAccessToken(), accessTokenResponseDto.getRefreshToken());
    }

    public List<RoleResponseDto> getAllRoles() {
        AdminTokenResponseDto adminTokenResponseDto = getAdminToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminTokenResponseDto.getAdminToken());

        ResponseEntity<RoleResponseDto[]> roleResponseDtoResponseEntity = restTemplate.exchange(ROLE_URL, HttpMethod.GET,
                new HttpEntity<>(headers), RoleResponseDto[].class);

        if (roleResponseDtoResponseEntity.getBody() != null) {
            return List.of(roleResponseDtoResponseEntity.getBody());
        }
        return Collections.EMPTY_LIST;
    }

    public UserInfoResponseDto getUserInfo(String username) {
        AdminTokenResponseDto adminTokenResponseDto = getAdminToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(adminTokenResponseDto.getAdminToken());


        ResponseEntity<JsonNode> response = restTemplate.exchange(String.format(USER_ID_URL, username), HttpMethod.GET,
                new HttpEntity<>(headers), JsonNode.class);
        var readerForUserInfoResponseDto = objectMapper.readerForListOf(UserInfoResponseDto.class);
        if (response.getBody() != null) {
            try {
                List<UserInfoResponseDto> userInfoResponseDtos = readerForUserInfoResponseDto.readValue(response.getBody());
                return userInfoResponseDtos.stream()
                        .findFirst().orElseThrow(() -> new RuntimeException("Could not find user"));
            } catch (IOException e) {
                throw new RuntimeException("Can't parse json data");
            }
        }
        return null;
    }

    public void assignUserRole(String username) {
        UserInfoResponseDto user = getUserInfo(username);
        List<RoleResponseDto> roles = getAllRoles();
        UUID userRoleId = roles.stream()
                .filter(roleResponseDto -> roleResponseDto.getName().equals(USER_ROLE_NAME))
                .map(RoleResponseDto::getId)
                .findFirst().orElseThrow(() -> new RuntimeException("USER role not found"));

        AdminTokenResponseDto adminTokenResponseDto = getAdminToken();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(adminTokenResponseDto.getAdminToken());

        List<UserRoleAssignDto> userRoleAssignDtos = Collections.singletonList(new UserRoleAssignDto(userRoleId, USER_ROLE_NAME));
        HttpEntity<List<UserRoleAssignDto>> request = new HttpEntity<>(userRoleAssignDtos, headers);

        restTemplate.exchange(String.format(ASSIGN_ROLE, user.getId()), HttpMethod.POST, request, String.class);
    }

    private static String getRequestBodyAsString(Map<String, String> requestBody) {
        StringBuilder formBody = new StringBuilder();
        requestBody.forEach((key, value) -> formBody.append(key).append("=").append(value).append("&"));
        return formBody.substring(0, formBody.length() - 1);
    }

}
