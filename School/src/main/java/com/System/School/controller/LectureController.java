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

import com.System.School.dto.LectureDto;
import com.System.School.service.LectureService;

@RestController
@RequestMapping("/api/lectures")
public class LectureController {
    

     @Autowired
    private LectureService lectureService;

    //add new Lecture
    @PostMapping("/create-lecture")
    public ResponseEntity<LectureDto> AddLecture(@RequestBody LectureDto dto) {
        try {
            LectureDto createLecture = lectureService.addLecture(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createLecture);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //get all lectures
    @GetMapping("/all-lectures")
    public ResponseEntity<List<LectureDto>> GetAllLectures() {
        try {
            List<LectureDto> lectures = lectureService.getAllLectures();
            return ResponseEntity.status(HttpStatus.OK).body(lectures);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    //get lecture
    @GetMapping("/get/{id}")
    public ResponseEntity<LectureDto> GetLecture(@PathVariable Long id) {
        try {
            LectureDto getLecture = lectureService.getByIdLecture(id);
            return ResponseEntity.ok(getLecture);
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    // update lecture
    @PutMapping("/update/{id}")
    public ResponseEntity<LectureDto> UpdateLecture(@PathVariable Long id,@RequestBody LectureDto dto) {
        try {
            LectureDto updatedLecture = lectureService.updateLecture(id, dto);
            return ResponseEntity.status(HttpStatus.OK).body(updatedLecture);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    //delete lecture
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> DeleteStudent(@PathVariable Long id) {
        try {
            lectureService.deleteLecture(id);
            return ResponseEntity.status(HttpStatus.OK).body("Ders silindi id: " + id);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add-student/{lectureId}/{studentId}")
    public ResponseEntity<String> AddStudentToLecture(@PathVariable Long lectureId, @PathVariable Long studentId) {
        try {
            lectureService.addStudentToLecture(lectureId, studentId);
            return ResponseEntity.status(HttpStatus.OK).body("Öğrenci Yeni Sınıfına Eklendi");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("/delete-student/{lectureId}/{studentId}")
    public ResponseEntity<String> DeleteStudentToLecture(@PathVariable Long lectureId, @PathVariable Long studentId) {
        try {
            lectureService.deleteStudentToLecture(lectureId, studentId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT)
                    .body("Öğrenci Sınıftan Başarılı Bir Şekilde Çıkarıldı.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/add-instructor/{lectureId}/{instructorId}")
    public ResponseEntity<String> AddInstructureToLecture(@PathVariable Long lectureId,
            @PathVariable Long instructorId) {
        try {
            lectureService.addInstructorToLecture(lectureId, instructorId);
            return ResponseEntity.status(HttpStatus.OK).body("Öğretmen Yeni Sınıfına Eklendi");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
    @DeleteMapping("/delete-instructor/{lectureId}/{instructorId}")
    public ResponseEntity<String> DeleteInstructureToLecture(@PathVariable Long lectureId, @PathVariable Long instructorId) {
        try {
            lectureService.deleteInstructorToLecture(lectureId, instructorId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Öğretmen Sınıftan Başarılı Bir Şekilde Çıkarıldı");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}
