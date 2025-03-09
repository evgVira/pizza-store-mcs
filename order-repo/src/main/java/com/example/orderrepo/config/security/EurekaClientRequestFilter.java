package com.example.orderrepo.config.security;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EurekaClientRequestFilter extends OncePerRequestFilter {

    private static final String INTERNAL_REQUEST = "internal";

    private final EurekaClient eurekaClient;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String remoteAddr = request.getRemoteAddr();
        List<String> registerdIp = getEurekaServiceIps();
        if (registerdIp.contains(remoteAddr)) {
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(INTERNAL_REQUEST, null, null)
            );
        }
        filterChain.doFilter(request, response);
    }

    private List<String> getEurekaServiceIps() {
        return eurekaClient.getApplications()
                .getRegisteredApplications()
                .stream()
                .flatMap(app -> app.getInstances().stream())
                .map(InstanceInfo::getIPAddr)
                .toList();
    }
}