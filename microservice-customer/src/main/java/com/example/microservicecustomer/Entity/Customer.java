package com.example.microservicecustomer.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jdk.jfr.DataAmount;
import lombok.Data;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue
    private Integer id;
    private String userName;
    private String dni;
    private Integer phone;
    private String email;
    private Long authId;

}
