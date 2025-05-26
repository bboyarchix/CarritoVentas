package com.example.microservicepedido.feign;

import com.example.microservicepedido.dto.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-catalogService", path = "/product")
public interface ProductFeign {
    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> ProductoPorId(@PathVariable(required = true) Long id);
}