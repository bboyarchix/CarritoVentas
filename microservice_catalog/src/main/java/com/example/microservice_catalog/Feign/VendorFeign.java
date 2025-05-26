package com.example.microservice_catalog.Feign;

import com.example.microservice_catalog.Dto.Vendor;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "microservice-vendorService", path = "/vendor")
public interface VendorFeign {

    @GetMapping("/{id}")
        public ResponseEntity<Vendor> getById(@PathVariable(required = true) Integer id);

}
