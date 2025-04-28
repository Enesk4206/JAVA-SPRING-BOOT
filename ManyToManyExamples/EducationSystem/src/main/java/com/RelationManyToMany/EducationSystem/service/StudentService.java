package com.RelationManyToMany.EducationSystem.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RelationManyToMany.EducationSystem.dto.StudentDTO;
import com.RelationManyToMany.EducationSystem.model.Course;
import com.RelationManyToMany.EducationSystem.model.Student;
import com.RelationManyToMany.EducationSystem.repository.CourseRepository;
import com.RelationManyToMany.EducationSystem.repository.StudentRepository;

@Service
public class StudentService {
    
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    //get all courses
    public List<StudentDTO> getAllStudents() {

        List<Student> students = studentRepository.findAll();
        return students.stream().map(
                student -> new StudentDTO(
                        student.getId(),
                        student.getName(),
                        student.getSurname(),
                        student.getAge(),
                        student.getCourses().stream().map(Course::getId).collect(Collectors.toSet())
                        ))
                .collect(Collectors.toList());
    }
    
    //get by id 
    public StudentDTO getByIdFromStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return new StudentDTO(
                student.getId(),
                student.getName(),
                student.getSurname(),
                student.getAge(),
                student.getCourses().stream().map(Course::getId).collect(Collectors.toSet())
        );
    }

    // add new Student
    public StudentDTO addNewStudent(StudentDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setSurname(dto.getSurname());
        student.setAge(dto.getAge());

        Student added = studentRepository.save(student);
        return new StudentDTO(
            added.getId(),
            added.getName(),
            added.getSurname(),
            added.getAge(),
            student.getCourses().stream().map(Course::getId).collect(Collectors.toSet())

        );

    }
    
    //update current Student

    public StudentDTO updateStudent(Long id, StudentDTO dto) {
        Student existedStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("This Student not found with id: " + id));

        existedStudent.setName(dto.getName());
        existedStudent.setSurname(dto.getSurname());
        existedStudent.setAge(dto.getAge());

        Student updated = studentRepository.save(existedStudent);
        return new StudentDTO(
                updated.getId(),
                updated.getName(),
                updated.getSurname(),
                updated.getAge(),
                updated.getCourses().stream().map(Course::getId).collect(Collectors.toSet())
        );
    }

    //delete exist Student

    public void deleteStudent(Long id) {
        Student existedStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("This Student not found with id: " + id));

        for (Course course : existedStudent.getCourses()) {
            course.getStudents().remove(existedStudent);
        }
        studentRepository.delete(existedStudent);
    }
    

     // Add course to student
     public void addCourseToStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        student.getCourses().add(course); // Add course to student
        course.getStudents().add(student); // Add student to course

        studentRepository.save(student);
        courseRepository.save(course);
    }

    // Remove course from student
    public void removeCourseFromStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + studentId));
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + courseId));

        student.getCourses().remove(course); // Remove course from student
        course.getStudents().remove(student); // Remove student from course

        studentRepository.save(student);
        courseRepository.save(course);
    }
}
