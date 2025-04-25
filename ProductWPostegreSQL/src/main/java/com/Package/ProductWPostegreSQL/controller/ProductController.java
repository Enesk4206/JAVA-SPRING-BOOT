package com.Package.ProductWPostegreSQL.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Package.ProductWPostegreSQL.dto.ProductDTO;
import com.Package.ProductWPostegreSQL.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    
    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getAll() {
        return productService.getAllProducts();
    }
    
    @GetMapping("{id}")
    public ResponseEntity<ProductDTO> getById(@PathVariable Long id) {
        ProductDTO dto = productService.getProductById(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }


    @PostMapping
    public ResponseEntity<ProductDTO> add(@RequestBody ProductDTO dto) {
        return ResponseEntity.ok(productService.addNewProduct(dto));
    }
    @PutMapping("{id}")
    public ResponseEntity<ProductDTO> update(@PathVariable Long id, @RequestBody ProductDTO dto) {
        ProductDTO updated = productService.updateProduct(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = productService.deleteProduct(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
