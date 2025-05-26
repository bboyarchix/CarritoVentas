package com.example.microservice_vendor.repository;

import com.example.microservice_vendor.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
}
