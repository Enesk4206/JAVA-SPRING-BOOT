package com.example.ProductCategory.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProductCategory.dto.CategoryDTO;
import com.example.ProductCategory.entity.Category;
import com.example.ProductCategory.repo.CategoryRepo;

@Service
public class CategoryService {
    
    @Autowired
    private CategoryRepo repoCategory;

    public Category addCategory(CategoryDTO categoryDto) {
        Category category = new Category();
        category.setName(categoryDto.getName());
        return repoCategory.save(category);

    }
    
    public List<Category> getAllCategory() {
        return repoCategory.findAll();
    }
}
