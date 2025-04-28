package com.System.School.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.System.School.dto.StudentDto;
import com.System.School.model.Lecture;
import com.System.School.model.Student;
import com.System.School.repository.StudentRepository;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    //add student 
    public StudentDto addStudent(StudentDto dto) {
        Student student = new Student();

        student.setStudentName(dto.getStudentName());
        student.setStudentSurname(dto.getStudentSurname());
        student.setStudentAge(dto.getStudentAge());
        student.setStudentEmail(dto.getStudentEmail());
        student.setLectures(new HashSet<>());

        Student created = studentRepository.save(student);
        return new StudentDto(
                created.getStudentId(),
                created.getStudentName(),
                created.getStudentSurname(),
                created.getStudentAge(),
                created.getStudentEmail(),
                created.getLectures().stream().map(Lecture::getLectureId).collect(Collectors.toSet())
        );

    }

    // get all students

    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();

        return students.stream().map(student -> new StudentDto(
                student.getStudentId(),
                student.getStudentName(),
                student.getStudentSurname(),
                student.getStudentAge(),
                student.getStudentEmail(),
                student.getLectures().stream().map(Lecture::getLectureId).collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }
    
    //get by id Student

    public StudentDto getByIdStudent(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Öğrenci bulunamadı id: " + id));

        return new StudentDto(
                student.getStudentId(),
                student.getStudentName(),
                student.getStudentSurname(),
                student.getStudentAge(),
                student.getStudentEmail(),
                student.getLectures().stream().map(Lecture::getLectureId).collect(Collectors.toSet())
        );
    }
    
    // update Student

    public StudentDto updateStudent(Long id, StudentDto dto) {

        Student student = studentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Öğrenci bulunamadı id: " + id));

        student.setStudentName(dto.getStudentName());
        student.setStudentSurname(dto.getStudentSurname());
        student.setStudentAge(dto.getStudentAge());
        student.setStudentEmail(dto.getStudentEmail());

        Student updated = studentRepository.save(student);
        return new StudentDto(
                updated.getStudentId(),
                updated.getStudentName(),
                updated.getStudentSurname(),
                updated.getStudentAge(),
                updated.getStudentEmail(),
                updated.getLectures().stream().map(Lecture::getLectureId).collect(Collectors.toSet()));
    }
    
    // delete Student

    public void deleteStudent(Long id) {
        Student existStudent = studentRepository.findById(id).orElseThrow(
            ()-> new RuntimeException("Silinmek istenen öğrenci bulunamadı id:"+id)
        );

        for (Lecture lecture : existStudent.getLectures()) {
            lecture.getStudents().remove(existStudent);
        }

        studentRepository.delete(existStudent);
    }
}
