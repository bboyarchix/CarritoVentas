package com.example.microservice_catalog.Service;

import com.example.microservice_catalog.Entity.SubCategory;

import java.util.List;
import java.util.Optional;

public interface SubCategoryService {

    public List<SubCategory> list();

    public SubCategory save(SubCategory SubCategory);

    public SubCategory update(SubCategory SubCategory);

    public Optional<SubCategory> findById(Integer id);

    public void deleteById(Integer id);

}