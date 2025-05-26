package com.example.microservice_catalog.Controller;


import com.example.microservice_catalog.Entity.Category;
import com.example.microservice_catalog.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
     private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<Category>> list() {
        return ResponseEntity.ok().body(categoryService.list());
    }
    @PostMapping()
    public ResponseEntity<Category> save(@RequestBody Category Category){
        return ResponseEntity.ok(categoryService.save(Category));
    }
    @PutMapping()
    public ResponseEntity<Category> update(@RequestBody Category Category){
        return ResponseEntity.ok(categoryService.update(Category));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> listById(@PathVariable(required = true) Integer id){
        return ResponseEntity.ok().body(categoryService.findById(id).get());
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(required = true) Integer id){
        categoryService.deleteById(id);
        return "Eliminacion Correcta";
    }

}
