package com.example.microserviceshoppincart.Service;


import com.example.microserviceshoppincart.Entity.ShoppingCart;

import java.util.List;
import java.util.Optional;

public interface ShoppingCartService {

    public List<ShoppingCart> list();
    public ShoppingCart save(ShoppingCart ShoppingCart);
    public ShoppingCart update(ShoppingCart ShoppingCart);
    public Optional<ShoppingCart> findById(Integer id);
    public void deleteById(Integer id);

}
