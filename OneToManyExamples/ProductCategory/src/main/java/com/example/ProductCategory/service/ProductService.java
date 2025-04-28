package com.example.ProductCategory.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ProductCategory.dto.ProductDTO;
import com.example.ProductCategory.entity.Category;
import com.example.ProductCategory.entity.Product;
import com.example.ProductCategory.repo.CategoryRepo;
import com.example.ProductCategory.repo.ProductRepo;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepo repoProduct;
    @Autowired
    private CategoryRepo repoCategory;

    public Product addNewProduct(ProductDTO productDto) {
        Optional<Category> categoryOptional = repoCategory.findById(productDto.getCategoryId());
        if (categoryOptional.isEmpty()) {
            throw new RuntimeException("Category not found with id: " + productDto.getCategoryId());
        }
        Product product = new Product();
        product.setName(productDto.getName());
        product.setCategory(categoryOptional.get());

        return repoProduct.save(product);

    }
    
    public List<Product> getAllProduct() {
        return repoProduct.findAll();
    }
}
