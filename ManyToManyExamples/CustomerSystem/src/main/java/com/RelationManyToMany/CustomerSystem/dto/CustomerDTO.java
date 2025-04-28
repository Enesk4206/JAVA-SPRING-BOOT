package com.RelationManyToMany.CustomerSystem.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerDTO {
    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private Boolean married;
    private String email;
    private String phoneNumber;

    private Set<Long> productIds;
}
