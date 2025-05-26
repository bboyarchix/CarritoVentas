package com.example.microserviceshoppincart.Controller;



import com.example.microserviceshoppincart.Entity.ShoppingCart;
import com.example.microserviceshoppincart.Service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("shoppingCart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    @GetMapping
    public ResponseEntity<List<ShoppingCart>> list() {

        return ResponseEntity.ok().body(shoppingCartService.list());
    }
    @PostMapping()
    public ResponseEntity<ShoppingCart> save(@RequestBody ShoppingCart ShoppingCart){
        return ResponseEntity.ok(shoppingCartService.save(ShoppingCart));
    }
    @PutMapping()
    public ResponseEntity<ShoppingCart> update(@RequestBody ShoppingCart ShoppingCart){
        return ResponseEntity.ok(shoppingCartService.update(ShoppingCart));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> listById(@PathVariable(required = true) Integer id) {
        Optional<ShoppingCart> cart = shoppingCartService.findById(id);
        return cart.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(required = true) Integer id){
        shoppingCartService.deleteById(id);
        return "Eliminacion Correcta";
    }
}
