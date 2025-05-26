package com.example.microservice_catalog.Service;

import com.example.microservice_catalog.Entity.SubCategory;
import com.example.microservice_catalog.Repository.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SubCategoryServiceImpl implements SubCategoryService {

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Override
    public List<SubCategory> list() {
        return subCategoryRepository.findAll();
    }

    @Override
    public SubCategory save(SubCategory SubCategory) {
        return subCategoryRepository.save(SubCategory);
    }

    @Override
    public SubCategory update(SubCategory SubCategory) {
        return subCategoryRepository.save(SubCategory);
    }

    @Override
    public Optional<SubCategory> findById(Integer id) {
        return subCategoryRepository.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        subCategoryRepository.deleteById(id);
    }
}
