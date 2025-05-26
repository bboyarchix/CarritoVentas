package com.example.microservice_payment.feign;

import com.example.microservice_payment.dto.PedidoDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "microservice-pedidoService", path = "/pedido")
public interface PedidoFeign {

    @GetMapping("/{id}")
    @CircuitBreaker(name = "pedidoListByIdCB", fallbackMethod = "pedidoListByIdFallback")
    ResponseEntity<PedidoDto> getPedidoById(@PathVariable Integer id);

    default ResponseEntity<PedidoDto> pedidoListByIdFallback(Integer id, Exception e) {
        return ResponseEntity.ok(new PedidoDto());
    }

    @PutMapping("/status/{orderId}")
    void updateOrderStatus(@PathVariable Integer orderId, @RequestParam String status);
}
