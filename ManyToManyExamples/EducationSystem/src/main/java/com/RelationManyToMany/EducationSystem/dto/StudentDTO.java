package com.RelationManyToMany.EducationSystem.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private Set<Long> courseIds;

    public StudentDTO(Long id, String name, String surname, int age, Set<Long> courseIds) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.courseIds = courseIds;
    }
}
