package com.example.microserviceinventory.Entity;

import com.example.microserviceinventory.Dto.Product;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer stock;
    private Integer stockMin;
    private Integer productId;

    @Transient
    private Product product;

    @LastModifiedDate
    private LocalDateTime lastUpdated;

}
