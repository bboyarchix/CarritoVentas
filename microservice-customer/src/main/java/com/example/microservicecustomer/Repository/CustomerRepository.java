package com.example.microservicecustomer.Repository;

import com.example.microservicecustomer.Entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
