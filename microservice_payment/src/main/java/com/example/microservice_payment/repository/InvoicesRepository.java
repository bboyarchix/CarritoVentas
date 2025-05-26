package com.example.microservice_payment.repository;

import com.example.microservice_payment.entity.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoicesRepository extends JpaRepository<Invoices, Integer> {
}
