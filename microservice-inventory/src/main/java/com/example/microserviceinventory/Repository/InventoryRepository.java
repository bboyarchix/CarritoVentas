package com.example.microserviceinventory.Repository;

import com.example.microserviceinventory.Entity.Inventory;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

public interface InventoryRepository extends JpaRepositoryImplementation<Inventory, Integer> {
}
