package com.example.microserviceshoppincart.Feign;

import com.example.microserviceshoppincart.Dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-catalogService", path = "/product")
public interface ProductFeign {
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(required = true) Integer id);
}
