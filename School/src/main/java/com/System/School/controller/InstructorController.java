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

import com.System.School.dto.InstructorDto;
import com.System.School.service.InstructorService;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {
     @Autowired
    private InstructorService instructorService;

    //add new instructor
    @PostMapping("/create-instructor")
    public ResponseEntity<InstructorDto> AddInstructor(@RequestBody InstructorDto dto) {
        try {
            InstructorDto createInstructor = instructorService.AddInstructor(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createInstructor);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //get all instructors
    @GetMapping("/all-instructors")
    public ResponseEntity<List<InstructorDto>> GetAllInstructors() {
        try {
            List<InstructorDto> instructors = instructorService.getAllInstructors();
            return ResponseEntity.status(HttpStatus.OK).body(instructors);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    //get ınstructor
    @GetMapping("/get/{id}")
    public ResponseEntity<InstructorDto> GetInstructor(@PathVariable Long id) {
        try {
            InstructorDto getInstructor = instructorService.getByIdInstructor(id);
            return ResponseEntity.ok(getInstructor);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    // update instructor
    @PutMapping("/update/{id}")
    public ResponseEntity<InstructorDto> UpdateInstructor(@PathVariable Long id,@RequestBody InstructorDto dto) {
        try {
            InstructorDto updatedInstructor = instructorService.updateInstructor(id, dto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedInstructor);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //delete instructor
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> DeleteInstructor(@PathVariable Long id) {
        try {
            instructorService.deleteInstructor(id);
            return ResponseEntity.status(HttpStatus.OK).body("Eğitmen silindi id: " + id);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
