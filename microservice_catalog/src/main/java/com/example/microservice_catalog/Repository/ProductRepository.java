package com.example.microservice_catalog.Repository;

import com.example.microservice_catalog.Entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface ProductRepository extends JpaRepositoryImplementation<Product, Integer> {
}
