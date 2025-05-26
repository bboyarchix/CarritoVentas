package com.example.microservicepedido.entity;


import com.example.microservicepedido.dto.CustomerDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private Float precioTotal;
    private String descripcion;
    private Long clienteId;
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PedidoDetalle> pedidoDetalles= new ArrayList<>();

    @Transient
    private CustomerDto customer;
}
