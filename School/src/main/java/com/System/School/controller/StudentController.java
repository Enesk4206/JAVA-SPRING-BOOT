package com.System.School.controller;

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

import com.System.School.dto.StudentDto;
import com.System.School.service.StudentService;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    //add new Student
    @PostMapping("/create-student")
    public ResponseEntity<StudentDto> AddStudent(@RequestBody StudentDto dto) {
        try {
            StudentDto createStudent = studentService.addStudent(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //get all students
    @GetMapping("/all-students")
    public ResponseEntity<List<StudentDto>> GetAllStudents() {
        try {
            List<StudentDto> students = studentService.getAllStudents();
            return ResponseEntity.status(HttpStatus.OK).body(students);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    //get student
    @GetMapping("/get/{id}")
    public ResponseEntity<StudentDto> GetStudent(@PathVariable Long id) {
        try {
            StudentDto getStudent = studentService.getByIdStudent(id);
            return ResponseEntity.ok(getStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    // update student
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentDto> UpdateStudent(@PathVariable Long id,@RequestBody StudentDto dto) {
        try {
            StudentDto updatedStudent = studentService.updateStudent(id, dto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //delete student
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> DeleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteStudent(id);
            return ResponseEntity.status(HttpStatus.OK).body("Öğrenci silindi id: " + id);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    

}
