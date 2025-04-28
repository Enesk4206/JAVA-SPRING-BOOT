package com.System.School.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.System.School.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
