package com.Package.ProductWPostegreSQL.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Package.ProductWPostegreSQL.dto.ProductDTO;
import com.Package.ProductWPostegreSQL.model.Product;
import com.Package.ProductWPostegreSQL.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    //create service
    public List<ProductDTO> getAllProducts() {
        try {
            return repository.findAll().stream()
                    .map(product -> new ProductDTO(product.getId(), product.getName(), product.getPrice()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error in getallproducts", e);
        }
    }

    //get product by Id
    public ProductDTO getProductById(Long id) {
        Optional<Product> optional = repository.findById(id);
        if (optional.isPresent()) {
            Product product = optional.get();
            return new ProductDTO(product.getId(),product.getName(), product.getPrice());

        }
        return null;
    }

    //create new product

    public ProductDTO addNewProduct(ProductDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        Product saved = repository.save(product);
        return new ProductDTO(saved.getId(), saved.getName(), saved.getPrice());
    }

    // update current product
    public ProductDTO updateProduct(Long id, ProductDTO dto) {
        Optional<Product> optional = repository.findById(id);
        if (optional.isPresent()) {
            Product product = optional.get();
            product.setName(dto.getName());
            product.setPrice(dto.getPrice());
            Product updated = repository.save(product);
            return new ProductDTO(updated.getId(), updated.getName(), updated.getPrice());
        }
        return null;
    }

    // delete product from id
    public boolean deleteProduct(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

}
