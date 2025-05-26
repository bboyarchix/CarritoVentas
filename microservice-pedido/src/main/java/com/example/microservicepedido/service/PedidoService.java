package com.example.microservicepedido.service;

import com.example.microservicepedido.entity.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoService {

    public List<Pedido> listar();

    public Pedido guardar(Pedido pedido);

    public Optional<Pedido> buscarPorId(Long id);

    public Pedido actualizar(Pedido pedido);

    public void eliminar(Long id);

    public boolean updateOrderStatus(Long id, String status);
}
