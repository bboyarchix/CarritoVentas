package com.example.microservice_catalog.Service;

import com.example.microservice_catalog.Entity.Category;
import com.example.microservice_catalog.Repository.CategoryRepository;
import com.example.microservice_catalog.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> list() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category Category) {
        return categoryRepository.save(Category);
    }

    @Override
    public Category update(Category Category) {
        return categoryRepository.save(Category);
    }

    @Override
    public Optional<Category> findById(Integer id) {

        return categoryRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        categoryRepository.deleteById(id);
    }
}
