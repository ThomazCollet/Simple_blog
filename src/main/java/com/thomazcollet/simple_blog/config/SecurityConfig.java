package com.thomazcollet.simple_blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/css/**",         // Libera arquivos CSS
                    "/js/**",          // Libera arquivos JS (se tiver)
                    "/images/**",      // Libera imagens
                    "/webjars/**",     // Libera recursos do Spring Boot se usar webjars
                    "/test.txt"        // Libera seu arquivo de teste
                ).permitAll()
                .anyRequest().permitAll()
            );
        return http.build();
    }
}

