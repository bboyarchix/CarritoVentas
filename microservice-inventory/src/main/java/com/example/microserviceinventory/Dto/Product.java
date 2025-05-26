package com.example.microserviceinventory.Dto;

import lombok.Data;

@Data
public class Product {
    private Long id;
    private String name;
    private String description;
    private SubCategory subCategory;
}
