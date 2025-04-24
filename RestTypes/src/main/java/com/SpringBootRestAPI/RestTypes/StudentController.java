package com.SpringBootRestAPI.RestTypes;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/student")
public class StudentController {
    
    private final Map<Integer, StudentDTo> studentMap = new HashMap<>();
    private int currentId =1;

    //CREATE(POST)
    @PostMapping
    public String addStudent(@RequestBody StudentDTo student) {
        student.setId(currentId);
        studentMap.put(currentId, student);
        return "Student added with Id: " + currentId++;
    }

    //GETALL(GET)
    @GetMapping
    public Collection<StudentDTo> getAllStudets() {
        return studentMap.values();
    }

    //GETBYID(GET)
    @GetMapping("/{id}")
    public StudentDTo getStudentById(@PathVariable int id) {
        return studentMap.get(id);
    }

    //UPDATE(PUT)
    @PutMapping("/{id}")
    public String updateString(@PathVariable int id, @RequestBody StudentDTo updatedStudent) {
        if (studentMap.containsKey(id)) {
            studentMap.put(id, updatedStudent);
            return "Student with ID " + id + " updated.";
        } else {
            return "Student not found";
        }
    }
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id) {
        if (studentMap.remove(id) != null) {
            return "Student with ID " + id + "deleted. ";
        } else {
            return "Student not found.";
        }
    }

}
