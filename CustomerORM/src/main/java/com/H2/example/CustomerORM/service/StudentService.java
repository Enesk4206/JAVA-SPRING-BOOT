package com.H2.example.CustomerORM.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.H2.example.CustomerORM.dto.StudentDTO;
import com.H2.example.CustomerORM.model.Student;
import com.H2.example.CustomerORM.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    
    public Student createStudent(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Long id, StudentDTO dtoDetails) {
        //if it is null student= null
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found");
        }
        student.setName(dtoDetails.getName());
        student.setEmail(dtoDetails.getEmail());
        return studentRepository.save(student);
    }

    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        
        if (student == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student Not Found");
        }
        studentRepository.delete(student);
    }

}
