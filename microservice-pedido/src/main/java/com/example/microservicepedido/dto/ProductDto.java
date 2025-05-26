package com.example.microservicepedido.dto;

import lombok.Data;

@Data
public class ProductDto {
    private Long id;          // ID del producto
    private String name;     // Nombre del producto
    private Float price;

}
