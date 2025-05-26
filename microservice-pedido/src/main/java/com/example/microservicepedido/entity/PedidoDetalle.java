package com.example.microservicepedido.entity;

import com.example.microservicepedido.dto.ProductDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PedidoDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer cantidad;
    private Float precioUnitario;
    private Long productoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "pedido_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Pedido pedido;

    @Transient
    private ProductDto producto;


}
