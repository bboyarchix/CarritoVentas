package com.example.microservicepedido.repository;

import com.example.microservicepedido.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    // Buscar todos los pedidos por un estado específico
    List<Pedido> findByStatus(String status);

    // Buscar un pedido específico por su ID y estado
    Optional<Pedido> findByIdAndStatus(Long id, String status);
}
