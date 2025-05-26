package com.example.microservicepedido.repository;

import com.example.microservicepedido.entity.PedidoDetalle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoDetalleRepository extends JpaRepository<PedidoDetalle, Long> {
}
