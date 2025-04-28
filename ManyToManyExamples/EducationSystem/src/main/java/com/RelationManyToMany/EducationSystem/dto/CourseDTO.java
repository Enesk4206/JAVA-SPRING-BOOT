package com.RelationManyToMany.EducationSystem.dto;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {
    private Long id;
    private String name;
    private String instructor;
    private double price;
    private int quota;
    private Set<Long> studentIds;

    public CourseDTO(Long id, String name, String instructor, double price, int quota,Set<Long> studentIds) {
        this.id = id;
        this.name = name;
        this.instructor = instructor;
        this.price = price;
        this.quota = quota;
        this.studentIds = studentIds;
        
    }
}
