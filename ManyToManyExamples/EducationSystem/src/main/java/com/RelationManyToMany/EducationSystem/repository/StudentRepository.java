package com.RelationManyToMany.EducationSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.RelationManyToMany.EducationSystem.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
