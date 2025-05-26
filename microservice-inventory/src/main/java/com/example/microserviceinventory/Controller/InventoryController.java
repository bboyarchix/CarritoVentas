package com.example.microserviceinventory.Controller;


import com.example.microserviceinventory.Entity.Inventory;
import com.example.microserviceinventory.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;
    
    @GetMapping
    public ResponseEntity<List<Inventory>> list() {
        return ResponseEntity.ok().body(inventoryService.list());
    }
    @PostMapping()
    public ResponseEntity<Inventory> save(@RequestBody Inventory Inventory){
        return ResponseEntity.ok(inventoryService.save(Inventory));
    }
    @PutMapping()
    public ResponseEntity<Inventory> update(@RequestBody Inventory Inventory){
        return ResponseEntity.ok(inventoryService.update(Inventory));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Inventory> listById(@PathVariable(required = true) Integer id){
        return ResponseEntity.ok().body(inventoryService.findById(id).get());
    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable(required = true) Integer id){
        inventoryService.deleteById(id);
        return "Eliminacion Correcta";
    }
}
