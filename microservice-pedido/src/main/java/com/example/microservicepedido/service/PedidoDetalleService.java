package com.example.microservicepedido.service;

import com.example.microservicepedido.entity.PedidoDetalle;

import java.util.List;
import java.util.Optional;

public interface PedidoDetalleService {

    public List<PedidoDetalle> listar();

    public PedidoDetalle guardar(PedidoDetalle pedidoDetalle);

    public Optional<PedidoDetalle> buscarPorId(Long id);

    public PedidoDetalle actualizar(PedidoDetalle pedidoDetalle);

    public void eliminar(Long id);

}
