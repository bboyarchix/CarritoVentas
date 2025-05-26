package com.example.microserviceinventory.Feign;

import com.example.microserviceinventory.Dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-catalogService", path = "/product")
public interface ProductFeign {
    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable(required = true) Integer id);
}
