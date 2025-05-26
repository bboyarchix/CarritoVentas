package com.example.microservice_catalog.Dto;

import lombok.Data;

@Data
public class Vendor {
    private Integer id;
    private String rasonSocial;
    private String formaJuridica;
    private String direccion;
    private String pais;
    private String numIdentificacion;
    private String identificacionFiscal;

}
