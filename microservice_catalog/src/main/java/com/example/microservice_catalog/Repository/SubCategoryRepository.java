package com.example.microservice_catalog.Repository;

import com.example.microservice_catalog.Entity.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
}
