package com.RelationManyToMany.CustomerSystem.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;


//ilişki yöneten tablo (customer)
@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private Boolean married;
    private String email;
    private String phoneNumber;

    @ManyToMany
    @JoinTable(
        name = "customer_products",
        joinColumns = @JoinColumn(name="customer_id"),
        inverseJoinColumns = @JoinColumn(name="product_id")
    )
    private Set<Product> products;

}
