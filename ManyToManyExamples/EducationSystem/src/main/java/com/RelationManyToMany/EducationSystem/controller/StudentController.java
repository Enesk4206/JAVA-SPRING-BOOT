package com.RelationManyToMany.EducationSystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.RelationManyToMany.EducationSystem.dto.StudentDTO;
import com.RelationManyToMany.EducationSystem.service.StudentService;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    
    @Autowired
    private StudentService studentService;

    //get all methode
    @GetMapping("/all-student")
    public ResponseEntity<List<StudentDTO>> GetAllCourses() {
        return  ResponseEntity.ok(studentService.getAllStudents());
    }

    //get by id course
    @GetMapping("{id}")
    public ResponseEntity<StudentDTO> GetCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(studentService.getByIdFromStudent(id));
    }

    //add new course
    @PostMapping("/create-student")
    public ResponseEntity<StudentDTO> CreateNewCourse(@RequestBody StudentDTO dto) {
        return ResponseEntity.ok(studentService.addNewStudent(dto));
    }

    //update current course
    @PutMapping("/update/{id}")
    public ResponseEntity<StudentDTO> UpdateCourse(@PathVariable Long id, @RequestBody StudentDTO dto) {
        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> DeleteCourse(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }

}
