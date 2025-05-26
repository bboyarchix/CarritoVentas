package com.example.microservice_vendor.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Vendor {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String rasonSocial;
    private String formaJuridica;
    private String direccion;
    private String pais;
    private String numIdentificacion;
    private String identificacionFiscal;
    private Long authId;
    private String userName;

}
