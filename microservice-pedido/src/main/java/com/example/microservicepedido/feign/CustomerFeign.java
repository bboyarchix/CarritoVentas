package com.example.microservicepedido.feign;

import com.example.microservicepedido.dto.CustomerDto;
import com.example.microservicepedido.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name = "microservice-customerService", path = "/customer")
public interface CustomerFeign {
    @GetMapping("/{id}")
    @CircuitBreaker(name = "clientListByIdCB", fallbackMethod = "clientListById")
    public ResponseEntity<CustomerDto> CustomerPorId(@PathVariable Long id);
    //default ResponseEntity<CustomerDto> clientListById(Long id, Exception e) {
    //    return ResponseEntity.ok(new CustomerDto());
    //}
}
