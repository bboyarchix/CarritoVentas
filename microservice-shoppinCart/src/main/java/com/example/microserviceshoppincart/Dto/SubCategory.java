package com.example.microserviceshoppincart.Dto;

import lombok.Data;

@Data
public class SubCategory {
    private Integer id;
    private String name;
    private Category category;
}
