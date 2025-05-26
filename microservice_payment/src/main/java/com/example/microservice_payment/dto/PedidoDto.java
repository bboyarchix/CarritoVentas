package com.example.microservice_payment.dto;

import lombok.Data;

@Data
public class PedidoDto {
    private Integer id;
    private String status;
    private Float precioTotal;
}
