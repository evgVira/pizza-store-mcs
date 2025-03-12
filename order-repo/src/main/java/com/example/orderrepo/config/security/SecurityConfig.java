package com.example.orderrepo.config.security;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    @Value("${jwk-uri}")
    private String JWK_URI;

    private static final String ADMIN_ROLE = "ADMIN";

    private static final String REALM_ACCESS = "realm_access";

    private static final String ROLES = "roles";

    private static final String INTERNAL_REQUEST = "internal";

    private final EurekaClientRequestFilter eurekaClientRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .addFilterAt(eurekaClientRequestFilter, SecurityContextPersistenceFilter.class)
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers(this::isEurekaClient).permitAll()
                                .requestMatchers("/api/v1/order/delete/*").hasRole(ADMIN_ROLE)
                                .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwtDecoder()));
        return httpSecurity.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(JWK_URI)
                .build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> authorities = extractRoles(jwt);
            return authorities;
        });
        return converter;
    }

    private Collection<GrantedAuthority> extractRoles(Jwt jwt) {
        Collection<GrantedAuthority> authorities = new JwtGrantedAuthoritiesConverter().convert(jwt);
        Map<String, Object> realmAccess = jwt.getClaim(REALM_ACCESS);
        if (realmAccess != null && realmAccess.containsKey(ROLES)) {
            List<String> roles = (List<String>) realmAccess.get(ROLES);
            authorities.addAll(roles.stream()
                    .map(role -> new SimpleGrantedAuthority(String.format("ROLE_%s", role)))
                    .toList());
        }
        return authorities;
    }

    private boolean isEurekaClient(HttpServletRequest request) {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals(INTERNAL_REQUEST);
    }
}
