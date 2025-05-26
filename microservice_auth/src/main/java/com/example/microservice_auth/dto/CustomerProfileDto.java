package com.example.microservice_auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerProfileDto {
    private Long authId;  // ID del usuario en el microservicio auth
    private String username;  // Nombre de usuario básico

}
