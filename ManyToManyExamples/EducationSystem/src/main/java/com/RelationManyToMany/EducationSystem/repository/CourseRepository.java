package com.RelationManyToMany.EducationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RelationManyToMany.EducationSystem.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    
}
