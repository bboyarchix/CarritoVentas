package com.example.microservice_catalog.Controller;

import com.example.microservice_catalog.Entity.Product;
import com.example.microservice_catalog.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok().body(productService.list());
    }
    @PostMapping()
    public ResponseEntity<Product> save(@RequestBody Product Product){
        return ResponseEntity.ok(productService.save(Product));
    }
    @PutMapping()
    public ResponseEntity<Product> update(@RequestBody Product Product){
        return ResponseEntity.ok(productService.update(Product));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> listById(@PathVariable(required = true) Integer id){
        return ResponseEntity.ok().body(productService.findById(id).get());
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(required = true) Integer id){
        productService.deleteById(id);
        return "Eliminacion Correcta";
    }
}
