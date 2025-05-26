package com.example.microservice_vendor.service;

import com.example.microservice_vendor.entity.Vendor;

import java.util.List;
import java.util.Optional;

public interface VendorService {
    public List<Vendor> list();
    public Vendor save(Vendor Vendor);
    public Vendor update(Vendor Vendor);
    public Optional<Vendor> findById(Integer id);
    public void deleteById(Integer id);

}
