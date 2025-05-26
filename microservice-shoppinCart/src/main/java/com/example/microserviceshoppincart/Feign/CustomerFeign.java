package com.example.microserviceshoppincart.Feign;

import com.example.microserviceshoppincart.Dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "microservice-customerService", path = "/customer")
public interface CustomerFeign {
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable(required = true) Integer id);
}
