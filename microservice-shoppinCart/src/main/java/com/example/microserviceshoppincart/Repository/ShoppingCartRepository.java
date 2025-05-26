package com.example.microserviceshoppincart.Repository;

import com.example.microserviceshoppincart.Entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {

    ShoppingCart findByCustomerId(Integer customerId);

}
