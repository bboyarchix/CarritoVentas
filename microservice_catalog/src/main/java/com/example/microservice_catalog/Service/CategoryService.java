package com.example.microservice_catalog.Service;

import com.example.microservice_catalog.Entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    public List<Category> list();
    public Category save(Category Category);
    public Category update(Category Category);
    public Optional<Category> findById(Integer id);
    public void deleteById(Integer id);

}
