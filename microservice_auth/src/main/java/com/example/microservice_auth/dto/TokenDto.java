package com.example.microservice_auth.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;

import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class TokenDto {
    private String token;


}
