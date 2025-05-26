package com.example.microserviceshoppincart.Service;


import com.example.microserviceshoppincart.Dto.Customer;
import com.example.microserviceshoppincart.Dto.Product;
import com.example.microserviceshoppincart.Entity.ShoppingCart;
import com.example.microserviceshoppincart.Entity.ShoppingCartItem;
import com.example.microserviceshoppincart.Feign.CustomerFeign;
import com.example.microserviceshoppincart.Feign.ProductFeign;
import com.example.microserviceshoppincart.Repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductFeign productFeign;

    @Autowired
    private CustomerFeign customerFeign;

    @Override
    public List<ShoppingCart> list() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public Optional<ShoppingCart> findById(Integer id) {
        return shoppingCartRepository.findById(id).map(shoppingCart -> {
            Customer customer = customerFeign.getCustomerById(shoppingCart.getCustomerId()).getBody();
            shoppingCart.setCustomer(customer);

            List<ShoppingCartItem> shoppingCartItems = shoppingCart.getShoppingCartItem().stream()
                    .map(shoppingCartItem -> {
                        Product product = productFeign.getProductById(shoppingCartItem.getProductId()).getBody();
                        shoppingCartItem.setProduct(product);
                        return shoppingCartItem;
                    })
                    .collect(Collectors.toList());
            shoppingCart.setShoppingCartItem(shoppingCartItems);

            return shoppingCart;
        });
    }

    @Override
    public ShoppingCart save(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }


    @Override
    public void deleteById(Integer id) {
        shoppingCartRepository.deleteById(id);
    }

    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {
        return shoppingCartRepository.save(shoppingCart);
    }
}
