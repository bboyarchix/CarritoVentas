package com.example.microserviceinventory.Service;

import com.example.microserviceinventory.Entity.Inventory;

import java.util.List;
import java.util.Optional;

public interface InventoryService {

    public List<Inventory> list();
    public Inventory save(Inventory Inventory);
    public Inventory update(Inventory Inventory);
    public Optional<Inventory> findById(Integer id);
    public void deleteById(Integer id);

}
