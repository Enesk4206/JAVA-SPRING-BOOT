package com.config.DTO.controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.DTO.dto.ProductDTO;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final Map<Integer, ProductDTO> productMap = new HashMap<>();
    private int currentId = 1;

    //add product
    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO product) {
        product.setId(currentId++);
        productMap.put(product.getId(), product);
        return product;
    }
    
    //get all product
    @GetMapping
    public Collection<ProductDTO> getAllProducts() {
        return productMap.values();
    }
    
    //get product by id
    @GetMapping("/{id}")
    public ProductDTO getProductById(@PathVariable int id, ProductDTO product) {

        if (productMap.containsKey(id)) {
            return productMap.get(id);
        } else {
            throw new Error("Hata");
        }
    }
    
    @PutMapping("/{id}")
    public ProductDTO updateProduct(@PathVariable int id, @RequestBody ProductDTO updatedProduct) {
        ProductDTO existingProduct = productMap.get(id);
        if (existingProduct != null) {
            updatedProduct.setId(id);
            productMap.put(id, updatedProduct);
            return updatedProduct;
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public String DeleteProduct(@PathVariable int id) {
        ProductDTO removed = productMap.remove(id);
        return (removed != null) ? "Product deleted successfully" : "Product not Found";
    }

}