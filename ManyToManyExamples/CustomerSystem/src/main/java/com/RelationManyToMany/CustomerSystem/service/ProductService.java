package com.RelationManyToMany.CustomerSystem.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RelationManyToMany.CustomerSystem.dto.ProductDTO;
import com.RelationManyToMany.CustomerSystem.entity.Customer;
import com.RelationManyToMany.CustomerSystem.entity.Product;
import com.RelationManyToMany.CustomerSystem.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    //add product 
    public ProductDTO addNewProduct(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setExpirationDate(dto.getExpirationDate());
        product.setCustomers(new HashSet<>());

        Product created = productRepository.save(product);
        return new ProductDTO(
                created.getId(),
                created.getName(),
                created.getPrice(),
                created.getExpirationDate(),
                created.getCustomers().stream().map(Customer::getId).collect(Collectors.toSet()));
    }
    //get by id product
    public ProductDTO getByIdProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found with id: " + id));
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getExpirationDate(),
                product.getCustomers().stream().map(Customer::getId).collect(Collectors.toSet()));
    }
    
    //get all product

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> new ProductDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getExpirationDate(),
                product.getCustomers().stream().map(Customer::getId).collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }
    
    //update product

    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Product product = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found with id: " + id));

        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setExpirationDate(dto.getExpirationDate());

        Product updated = productRepository.save(product);
        return new ProductDTO(
                product.getId(),
                updated.getName(),
                updated.getPrice(),
                updated.getExpirationDate(),
                updated.getCustomers().stream().map(Customer::getId).collect(Collectors.toSet()));
    }
    

    //delete product
    public void deleteProduct(Long id) {
        Product existedProduct = productRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Product not found with id: " + id));
            
        for (Customer customer : existedProduct.getCustomers()) {
            customer.getProducts().remove(existedProduct);
        }

        productRepository.delete(existedProduct);
    }

}
