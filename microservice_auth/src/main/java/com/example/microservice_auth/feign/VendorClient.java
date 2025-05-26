package com.example.microservice_auth.feign;
import com.example.microservice_auth.dto.CustomerProfileDto;
import com.example.microservice_auth.dto.VendorProfileDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "microservice-vendorService", path = "/vendor")
public interface VendorClient {
    @PostMapping("/create")
    void createVendorProfile(@RequestBody VendorProfileDto vendorProfileDto);
}
