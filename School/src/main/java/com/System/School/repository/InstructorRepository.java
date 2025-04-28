package com.System.School.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.System.School.model.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
    
}
