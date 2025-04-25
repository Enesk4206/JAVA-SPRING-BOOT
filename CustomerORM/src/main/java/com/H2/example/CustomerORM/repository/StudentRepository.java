package com.H2.example.CustomerORM.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.H2.example.CustomerORM.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long >{

}
