package com.coursework.ticketbookingsystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable CSRF protection
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Allow unauthenticated access to these URLs
                        .requestMatchers("/api/auth/signup", "/api/auth/login", "/api/ticket/info","api/ticket/customer/data", "api/ticket/configuration","api/ticket/customer/buy", "api/system/start", "api/system/stop").permitAll()
                        // All other requests require authentication
                        .anyRequest().authenticated()
                )
                // Enable basic HTTP authentication (can be replaced with JWT/OAuth2 if needed)
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
