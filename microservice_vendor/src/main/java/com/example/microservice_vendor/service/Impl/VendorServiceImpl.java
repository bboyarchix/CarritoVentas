package com.example.microservice_vendor.service.Impl;

import com.example.microservice_vendor.entity.Vendor;
import com.example.microservice_vendor.repository.VendorRepository;
import com.example.microservice_vendor.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public List<Vendor> list() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor save(Vendor Vendor) {
        return vendorRepository.save(Vendor);
    }

    @Override
    public Vendor update(Vendor Vendor) {
        return vendorRepository.save(Vendor);
    }

    @Override
    public Optional<Vendor> findById(Integer id) {
        return vendorRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        vendorRepository.deleteById(id);
    }
}
