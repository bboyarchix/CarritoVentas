package com.example.microservice_catalog.Controller;

import com.example.microservice_catalog.Entity.SubCategory;
import com.example.microservice_catalog.Service.SubCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("subCategory")
public class SubCategoryController {
    
    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping
    public ResponseEntity<List<SubCategory>> list() {
        return ResponseEntity.ok().body(subCategoryService.list());
    }
    @PostMapping()
    public ResponseEntity<SubCategory> save(@RequestBody SubCategory SubCategory){
        return ResponseEntity.ok(subCategoryService.save(SubCategory));
    }
    @PutMapping()
    public ResponseEntity<SubCategory> update(@RequestBody SubCategory SubCategory){
        return ResponseEntity.ok(subCategoryService.update(SubCategory));
    }
    @GetMapping("/{id}")
    public ResponseEntity<SubCategory> listById(@PathVariable(required = true) Integer id){
        return ResponseEntity.ok().body(subCategoryService.findById(id).get());
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(required = true) Integer id){
        subCategoryService.deleteById(id);
        return "Eliminacion Correcta";
    }
    
}
