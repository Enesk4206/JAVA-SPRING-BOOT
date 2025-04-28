package com.System.School.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.System.School.dto.LectureDto;
import com.System.School.model.Instructor;
import com.System.School.model.Lecture;
import com.System.School.model.Student;
import com.System.School.repository.InstructorRepository;
import com.System.School.repository.LectureRepository;
import com.System.School.repository.StudentRepository;

@Service
public class LectureService {
    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    //add lecture 
    public LectureDto addLecture(LectureDto dto) {
        Lecture lecture = new Lecture();

        lecture.setLectureName(dto.getLectureName());
        lecture.setLectureStartTime(dto.getLectureStartTime());
        lecture.setStudents(new HashSet<>());
        lecture.setInstructor(null);

       

        Lecture created = lectureRepository.save(lecture);
        
        return new LectureDto(
            created.getLectureId(),
            created.getLectureName(),
            created.getLectureStartTime(),
            created.getStudents().stream().map(Student::getStudentId).collect(Collectors.toSet()),
            created.getInstructor() != null ? created.getInstructor().getInstructorId() : null
        );
            
    }

    // get all lectures

    public List<LectureDto> getAllLectures() {
        List<Lecture> lectures = lectureRepository.findAll();

        return lectures.stream().map(lecture -> new LectureDto(
                lecture.getLectureId(),
                lecture.getLectureName(),
                lecture.getLectureStartTime(),
                lecture.getStudents().stream().map(Student::getStudentId).collect(Collectors.toSet()),
                lecture.getInstructor() != null ? lecture.getInstructor().getInstructorId() : null
        )).collect(Collectors.toList());
    }
    
    //get by id lecture

    public LectureDto getByIdLecture(Long id) {
        Lecture lecture = lectureRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Ders bulunamadı id: " + id));
                return new LectureDto(
                    lecture.getLectureId(),
                    lecture.getLectureName(),
                    lecture.getLectureStartTime(),
                    lecture.getStudents().stream().map(Student::getStudentId).collect(Collectors.toSet()),
                    lecture.getInstructor() != null ? lecture.getInstructor().getInstructorId() : null
                );
                
    }
    
    // update Lecture

    public LectureDto updateLecture(Long id, LectureDto dto) {

        Lecture lecture = lectureRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Ders bulunamadı id: " + id));

        lecture.setLectureName(dto.getLectureName());
        lecture.setLectureStartTime(dto.getLectureStartTime());
            
        Lecture updated = lectureRepository.save(lecture);
        return new LectureDto(
            updated.getLectureId(),
            updated.getLectureName(),
            updated.getLectureStartTime(),
            updated.getStudents().stream().map(Student::getStudentId).collect(Collectors.toSet()),
            updated.getInstructor() != null ? updated.getInstructor().getInstructorId() : null
        );
    }
    
    // delete lecture

    public void deleteLecture(Long id) {
        Lecture existLecture = lectureRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Silinmek istenen ders bulunamadı id:" + id));

        for (Student student : existLecture.getStudents()) {
            student.getLectures().remove(existLecture);
        }

        lectureRepository.delete(existLecture);
    }
    
    //add student to lecture
    public void addStudentToLecture(Long lectureId, Long studentId) {
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
                () -> new RuntimeException("Ders Bulunamadı id: " + lectureId));

        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new RuntimeException("Öğrenci Bulunamadı id: " + studentId));

        lecture.getStudents().add(student);
        student.getLectures().add(lecture);

        lectureRepository.save(lecture);
        studentRepository.save(student);
    }
    
    //delete student to lecture
    public void deleteStudentToLecture(Long lectureId, Long studentId) {
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
                () -> new RuntimeException("Ders Bulunamadı id: " + lectureId));

        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new RuntimeException("Öğrenci Bulunamadı id: " + studentId));

        lecture.getStudents().remove(student);
        student.getLectures().remove(lecture);

        lectureRepository.save(lecture);
        studentRepository.save(student);
    }


    //add instructor to lecture
    public void addInstructorToLecture(Long lectureId, Long instructureId) {
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
                () -> new RuntimeException("Ders Bulunamadı id: " + lectureId));

        Instructor instructor = instructorRepository.findById(instructureId).orElseThrow(
                () -> new RuntimeException("Eğitmen bulunamadı id: " + instructureId));

        lecture.setInstructor(instructor);
        instructor.getLectures().add(lecture);

        lectureRepository.save(lecture);
        instructorRepository.save(instructor);
    }
    
    //delete instructor to lecture
    public void deleteInstructorToLecture(Long lectureId, Long instructureId) {
        Lecture lecture = lectureRepository.findById(lectureId).orElseThrow(
                () -> new RuntimeException("Ders Bulunamadı id: " + lectureId));
        
        Instructor instructor = instructorRepository.findById(instructureId).orElseThrow(
                () -> new RuntimeException("Eğitmen bulunamadı id: " + instructureId));

        lecture.setInstructor(null);
        instructor.getLectures().add(lecture);

        lectureRepository.save(lecture);
        instructorRepository.save(instructor);
    }
}
