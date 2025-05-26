package com.example.microservicepedido.service.Impl;

import com.example.microservicepedido.dto.ProductDto;
import com.example.microservicepedido.entity.Pedido;
import com.example.microservicepedido.entity.PedidoDetalle;
import com.example.microservicepedido.feign.CustomerFeign;
import com.example.microservicepedido.feign.ProductFeign;
import com.example.microservicepedido.repository.PedidoDetalleRepository;
import com.example.microservicepedido.service.PedidoDetalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PedidoDetalleServiceImpl implements PedidoDetalleService {

    @Autowired
    private PedidoDetalleRepository pedidoDetalleRepository;



    @Autowired
    private ProductFeign productFeign;

    @Override
    public List<PedidoDetalle> listar() {
        return pedidoDetalleRepository.findAll();
    }

    @Override
    public PedidoDetalle guardar(PedidoDetalle pedidoDetalle) {
        return pedidoDetalleRepository.save(pedidoDetalle);
    }

    @Override
    public Optional<PedidoDetalle> buscarPorId(Long id) {
        Optional<PedidoDetalle> pedidoDetalle = pedidoDetalleRepository.findById(id);
        pedidoDetalle.get().setProducto(productFeign.ProductoPorId(pedidoDetalle.get().getProductoId()).getBody());
        return pedidoDetalle;
    }


    @Override
    public PedidoDetalle actualizar(PedidoDetalle pedidoDetalle) {
        return pedidoDetalleRepository.save(pedidoDetalle);
    }

    @Override
    public void eliminar(Long id) {
        pedidoDetalleRepository.deleteById(id);

    }



}
