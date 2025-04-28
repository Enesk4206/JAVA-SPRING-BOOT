package com.RelationManyToMany.CustomerSystem.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private double price;
    private LocalDate expirationDate;


    private Set<Long> customerIds;

}
