package com.H2.example.CustomerORM.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.H2.example.CustomerORM.dto.StudentDTO;
import com.H2.example.CustomerORM.model.Student;
import com.H2.example.CustomerORM.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService service;

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody StudentDTO dto) {
        return new ResponseEntity<>(service.createStudent(dto), HttpStatus.CREATED);
    }
    @GetMapping
    public List<Student> getAll() {
        return service.getAllStudents();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable Long id, @RequestBody StudentDTO dto) {
        Student updatedStudent = service.updateStudent(id, dto);
        return new ResponseEntity<>(updatedStudent, HttpStatus.OK);
        
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

       try {
           service.deleteStudent(id);
           return new ResponseEntity<>(HttpStatus.NO_CONTENT);
       } catch (ResponseStatusException e) {
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
    }
}
