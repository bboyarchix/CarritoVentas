package com.example.microservice_auth.feign;

import com.example.microservice_auth.dto.CustomerProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-customerService", path = "/customer")
public interface CustomerClient {
    @PostMapping("/create")
    ResponseEntity<Void> createCustomerProfile(@RequestBody CustomerProfileDto customerProfileDto);
}

