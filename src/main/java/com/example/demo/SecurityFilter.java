package com.example.demo;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.example.demo.SecurityConfig.decodeToken;

public class SecurityFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        System.out.println("Executing custom Security filter...");

        String headerAuth = request.getHeader("Authorization");

        if (headerAuth != null) {
            Authentication authorization = decodeToken(request);

            if (authorization != null) {
                SecurityContextHolder.getContext().setAuthentication(authorization);

            } else {
                response.setStatus(401);
                response.setContentType("application/json");
                response.getWriter().println("Auth info incorrect");
                response.getWriter().flush();

                System.out.println("Custom Security filter executed...");

                return;
            }
        }
        System.out.println("Custom Security filter executed...");
        filterChain.doFilter(request, response);
    }
}
