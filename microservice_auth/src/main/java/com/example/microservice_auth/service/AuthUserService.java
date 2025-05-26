package com.example.microservice_auth.service;

import com.example.microservice_auth.dto.LoginRequest;
import com.example.microservice_auth.dto.RegisterRequest;
import com.example.microservice_auth.dto.TokenDto;
import com.example.microservice_auth.entity.AuthUser;

public interface AuthUserService {

    TokenDto login(LoginRequest loginRequest) throws Exception;

    AuthUser registerUser(RegisterRequest registerRequest);

    TokenDto validateToken(String token);


    void logout(String token);
}