package com.example.microserviceshoppincart.Dto;

import lombok.Data;

@Data
public class Customer {
    private Integer id;
    private String name;
    private String dni;
    private String phone;
    private String email;
}
