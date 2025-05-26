package com.example.microservicepedido.controller;

import com.example.microservicepedido.entity.PedidoDetalle;
import com.example.microservicepedido.service.PedidoDetalleService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidoDetalle")
public class PedidoDetalleController {
    @Autowired
    private PedidoDetalleService pedidoDetalleService;

    @GetMapping
    public ResponseEntity<List<PedidoDetalle>> listar() {
        return ResponseEntity.ok(pedidoDetalleService.listar());
    }

    @PostMapping
    public ResponseEntity<PedidoDetalle> guardar(@RequestBody PedidoDetalle pedidoDetalle) {
        return ResponseEntity.ok(pedidoDetalleService.guardar(pedidoDetalle));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDetalle> buscarPOrId(@PathVariable(required = true) Long id) {
        return ResponseEntity.ok(pedidoDetalleService.buscarPorId(id).get());
    }

    @PutMapping
    public ResponseEntity<PedidoDetalle> actualizar(@RequestBody PedidoDetalle pedidoDetalle) {
        return ResponseEntity.ok(pedidoDetalleService.actualizar(pedidoDetalle));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<PedidoDetalle>> eliminar(@PathVariable(required = true) Long id) {
        pedidoDetalleService.eliminar(id);
        return ResponseEntity.ok(pedidoDetalleService.listar());
    }
}
