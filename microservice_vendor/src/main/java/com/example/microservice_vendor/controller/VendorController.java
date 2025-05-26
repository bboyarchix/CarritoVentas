package com.example.microservice_vendor.controller;

import com.example.microservice_vendor.dto.VendorProfileDto;
import com.example.microservice_vendor.entity.Vendor;
import com.example.microservice_vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vendor")
public class VendorController {
    @Autowired
    private VendorService vendorService;
    @GetMapping
    public ResponseEntity<List<Vendor>> list() {
        return ResponseEntity.ok().body(vendorService.list());
    }
    @PostMapping()
    public ResponseEntity<Vendor> save(@RequestBody Vendor vendor){
        return ResponseEntity.ok(vendorService.save(vendor));
    }
    @PostMapping("/create")
    public ResponseEntity<Vendor> createProfile(@RequestBody VendorProfileDto profileDto) {
        Vendor vendor = new Vendor();
        vendor.setAuthId(profileDto.getAuthId());
        vendor.setUserName(profileDto.getUsername());
        return ResponseEntity.ok(vendorService.save(vendor));
    }
    @PutMapping()
    public ResponseEntity<Vendor> update(@RequestBody Vendor vendor){
        return ResponseEntity.ok(vendorService.update(vendor));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Vendor> listById(@PathVariable(required = true) Integer id){
        return ResponseEntity.ok().body(vendorService.findById(id).get());
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(required = true) Integer id){
        vendorService.deleteById(id);
        return "Eliminacion Correcta";
    }
}
