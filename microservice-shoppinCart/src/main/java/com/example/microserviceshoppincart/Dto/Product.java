package com.example.microserviceshoppincart.Dto;

import lombok.Data;

@Data
public class Product {
    private Integer id;
    private String name;
    private String description;
    private SubCategory subCategory;
}
