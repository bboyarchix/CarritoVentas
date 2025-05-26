package com.example.microservice_catalog.Entity;

import com.example.microservice_catalog.Dto.Vendor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private BigDecimal price; //
    private String image;
    private Integer vendorId;

    @Transient // Este campo no se guarda en la base de datos, solo se usa para comunicación
    private Vendor vendor;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subCategory_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private SubCategory subCategory;


}
