package com.example.microservice_catalog.Repository;

import com.example.microservice_catalog.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
