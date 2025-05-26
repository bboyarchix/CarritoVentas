package com.example.microserviceshoppincart.Entity;

import com.example.microserviceshoppincart.Dto.Customer;
import com.example.microserviceshoppincart.Dto.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EntityListeners(AuditingEntityListener.class)
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer customerId;

    // Relación uno a muchos (One ShoppingCart -> Many ShoppingCartItems)
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JoinColumn(name = "shopping_cart_id") // Asegúrate de que este nombre sea correcto
    private List<ShoppingCartItem> shoppingCartItem;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "last_updated")
    private LocalDateTime lastUpdated;

    // Este campo sigue siendo Transient porque el cliente se obtiene vía Feign y no se almacena en la DB
    @Transient
    private Customer customer;
}
