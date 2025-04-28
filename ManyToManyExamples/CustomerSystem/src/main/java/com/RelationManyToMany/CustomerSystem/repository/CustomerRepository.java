package com.RelationManyToMany.CustomerSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RelationManyToMany.CustomerSystem.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
