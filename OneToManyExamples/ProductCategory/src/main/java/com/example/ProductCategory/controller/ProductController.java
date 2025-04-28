package com.example.ProductCategory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ProductCategory.dto.ProductDTO;
import com.example.ProductCategory.entity.Product;
import com.example.ProductCategory.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @PostMapping()
    public Product createProduct(@RequestBody ProductDTO productDTO) {
        return productService.addNewProduct(productDTO);
    }
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProduct();
    }
}
