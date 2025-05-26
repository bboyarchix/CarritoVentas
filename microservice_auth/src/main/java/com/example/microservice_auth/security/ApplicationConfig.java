package com.example.microservice_auth.security;

import com.example.microservice_auth.repository.AuthUserRepository;
import com.example.microservice_auth.service.impl.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final AuthUserRepository authUserRepository;
    @Bean
   public UserDetailsService userDetailsService() {
       return new CustomUserDetailsService(authUserRepository);
   }

}