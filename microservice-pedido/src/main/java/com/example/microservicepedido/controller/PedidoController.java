package com.example.microservicepedido.controller;

import com.example.microservicepedido.dto.CustomerDto;
import com.example.microservicepedido.dto.ErrorResponseDto;
import com.example.microservicepedido.dto.ProductDto;
import com.example.microservicepedido.entity.Pedido;
import com.example.microservicepedido.entity.PedidoDetalle;
import com.example.microservicepedido.feign.CustomerFeign;
import com.example.microservicepedido.feign.ProductFeign;
import com.example.microservicepedido.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController {
    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private CustomerFeign customerFeign;

    @Autowired
    private ProductFeign productFeign;

    @GetMapping
    public ResponseEntity<List<Pedido>> listar() {
        return ResponseEntity.ok(pedidoService.listar());
    }

    @PostMapping
    public ResponseEntity<?> guardar(@RequestBody Pedido pedido) {
        ResponseEntity<CustomerDto> customerResponse = customerFeign.CustomerPorId(pedido.getClienteId());
        CustomerDto customerDto = customerResponse.getBody();

        if (customerDto == null || customerDto.getId() == null) {
            String errorMessage = "Error: Customer no encontrado";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
        }

        for (PedidoDetalle pedidoDetalle : pedido.getPedidoDetalles()) {
            ResponseEntity<ProductDto> productResponse = productFeign.ProductoPorId(pedidoDetalle.getProductoId());
            ProductDto productDto = productResponse.getBody();

            if (productDto == null || productDto.getId() == null) {
                String errorMessage = "Error: Product not encontrado.";
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDto(errorMessage));
            }
        }

        Pedido newOrder = pedidoService.guardar(pedido);
        return ResponseEntity.ok(newOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> buscarPorId(@PathVariable(required = true) Long id) {
        return ResponseEntity.ok(pedidoService.buscarPorId(id).get());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pedido> editar(@PathVariable(required = true) Long id,
                                         @RequestBody Pedido pedido) {
        pedido.setId(id);
        return ResponseEntity.ok(pedidoService.actualizar(pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<List<Pedido>> eliminar(@PathVariable(required = true) Long id) {
        pedidoService.eliminar(id);
        return ResponseEntity.ok(pedidoService.listar());
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<String> updateOrderStatus(@PathVariable("id") Long id, @RequestParam("status") String status) {
        boolean updated = pedidoService.updateOrderStatus(id, status);
        if (updated) {
            return ResponseEntity.ok("Order status updated successfully.");
        } else {
            return ResponseEntity.status(404).body("Order not found.");
        }
    }
}