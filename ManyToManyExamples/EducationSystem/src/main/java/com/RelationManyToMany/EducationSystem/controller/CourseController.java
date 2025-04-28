package com.RelationManyToMany.EducationSystem.controller;

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

import com.RelationManyToMany.EducationSystem.dto.CourseDTO;
import com.RelationManyToMany.EducationSystem.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {
    
    @Autowired
    private CourseService courseService;

    // Get all courses
    @GetMapping("/all-course")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        List<CourseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);  // 200 OK
    }

    // Get course by id
    @GetMapping("{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        try {
            CourseDTO course = courseService.getByIdFromCourse(id);
            return ResponseEntity.ok(course);  // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // 404 Not Found
        }
    }

    // Add new course
    @PostMapping("/create-course")
    public ResponseEntity<String> createNewCourse(@RequestBody CourseDTO dto) {
        CourseDTO createdCourse = courseService.addNewCourse(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Course created successfully with ID: " + createdCourse.getId());  // 201 Created
    }

    // Update existing course
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCourse(@PathVariable Long id, @RequestBody CourseDTO dto) {
        try {
            CourseDTO updatedCourse = courseService.updateCourse(id, dto);
            return ResponseEntity.ok("Course updated successfully with ID: " + updatedCourse.getId());  // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found with ID: " + id);  // 404 Not Found
        }
    }

    // Delete course
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable Long id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Course deleted successfully");  // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course not found with ID: " + id);  // 404 Not Found
        }
    }

    // Add student to a course
    @PostMapping("/add-student/{courseId}/{studentId}")
    public ResponseEntity<String> addStudentToCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        try {
            courseService.addStudentToCourse(courseId, studentId);
            return ResponseEntity.ok("Student added to course successfully");  // 200 OK
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course or Student not found with IDs: " + courseId + ", " + studentId);  // 404 Not Found
        }
    }

    // Remove student from a course
    @DeleteMapping("/remove-student/{courseId}/{studentId}")
    public ResponseEntity<String> removeStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        try {
            courseService.remoweStudentFromCourse(courseId, studentId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Student removed from course successfully");  // 204 No Content
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course or Student not found with IDs: " + courseId + ", " + studentId);  // 404 Not Found
        }
    }
}
