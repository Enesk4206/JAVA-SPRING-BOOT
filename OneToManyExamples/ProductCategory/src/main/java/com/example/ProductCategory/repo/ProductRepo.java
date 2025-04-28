package com.example.ProductCategory.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ProductCategory.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long> {
    
}
