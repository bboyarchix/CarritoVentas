package com.example.microserviceshoppincart.Entity;


import com.example.microserviceshoppincart.Dto.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class ShoppingCartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer quantity;
    private Integer productId;


    @Transient
    private Product product;  // Se obtiene vía Feign, no se persiste en la DB

    @LastModifiedDate
    private LocalDateTime lastUpdated;

}
