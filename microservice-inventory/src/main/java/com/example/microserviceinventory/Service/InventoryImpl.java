package com.example.microserviceinventory.Service;

import com.example.microserviceinventory.Dto.Product;
import com.example.microserviceinventory.Entity.Inventory;
import com.example.microserviceinventory.Feign.ProductFeign;
import com.example.microserviceinventory.Repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class InventoryImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductFeign productFeign;

    @Override
    public List<Inventory> list() {
        return inventoryRepository.findAll();
    }

    @Override
    public Inventory save(Inventory Inventory) {
        return inventoryRepository.save(Inventory);
    }

    @Override
    public Inventory update(Inventory Inventory) {
        return inventoryRepository.save(Inventory);
    }

    @Override
    public Optional<Inventory> findById(Integer id) {
        Optional<Inventory> inventoryOptional = inventoryRepository.findById(id);

        if (!inventoryOptional.isPresent()) {
            return Optional.empty(); // No se encontró el inventario
        }
        Inventory inventory = inventoryOptional.get();
        ResponseEntity<Product> productResponse = productFeign.getById(inventory.getProductId());

        if (productResponse.getBody() != null) {
            inventory.setProduct(productResponse.getBody());
        } else {
            // Manejar el caso cuando el producto no se encuentra, si es necesario
            inventory.setProduct(null);
        }
        return Optional.of(inventory);
    }


    @Override
    public void deleteById(Integer id) {
        inventoryRepository.deleteById(id);
    }
}
