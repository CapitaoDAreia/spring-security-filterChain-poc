package com.example.demo;

import jakarta.servlet.http.HttpServletRequest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(@NotNull HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                authorize -> authorize
                        .requestMatchers(HttpMethod.GET, "/api/allowed").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/authMethod").authenticated()
        );

        http.addFilterBefore(new SecurityFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    public static @Nullable Authentication decodeToken(@NotNull HttpServletRequest request) {
        if (request.getHeader("Authorization").equals("Bearer token123")) {
            return new UsernamePasswordAuthenticationToken("user", null, null);
        }

        return null;
    }
}
