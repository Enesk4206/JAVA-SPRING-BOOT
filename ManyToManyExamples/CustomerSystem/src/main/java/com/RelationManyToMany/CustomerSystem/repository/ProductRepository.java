package com.RelationManyToMany.CustomerSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RelationManyToMany.CustomerSystem.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {
    
}
