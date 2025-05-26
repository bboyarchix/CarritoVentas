package com.example.microservice_payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.Date;

@Data
@Entity
public class Invoices {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer orderId;
    private Integer customerId;
    private Integer paymentId;

    private Date invoiceDate;
    private Float totalAmount;
    private Float taxAmount; //monto total de impuestos


}
