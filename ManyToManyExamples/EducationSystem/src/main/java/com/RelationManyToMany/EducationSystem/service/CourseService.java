package com.RelationManyToMany.EducationSystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.RelationManyToMany.EducationSystem.dto.CourseDTO;
import com.RelationManyToMany.EducationSystem.model.Course;
import com.RelationManyToMany.EducationSystem.model.Student;
import com.RelationManyToMany.EducationSystem.repository.CourseRepository;
import com.RelationManyToMany.EducationSystem.repository.StudentRepository;

@Service
public class CourseService {
    
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private StudentRepository studentRepository;

    //get all courses
    public List<CourseDTO> getAllCourses() {

        List<Course> courses = courseRepository.findAll();
        return courses.stream().map(
                course -> new CourseDTO(
                        course.getId(),
                        course.getName(),
                        course.getInstructor(),
                        course.getPrice(),
                        course.getQuota(),
                        course.getStudents().stream().map(Student::getId).collect(Collectors.toSet())    //i             
                        ))
                .collect(Collectors.toList());
    }
    
    //get by id 
    public CourseDTO getByIdFromCourse(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
        return new CourseDTO(
                        course.getId(),
                        course.getName(),
                        course.getInstructor(),
                        course.getPrice(),
                        course.getQuota(),
                        course.getStudents().stream().map(Student::getId).collect(Collectors.toSet()) //i
                );
    }

    // add new Course
    public CourseDTO addNewCourse(CourseDTO dto) {
        Course course = new Course();
        course.setName(dto.getName());
        course.setInstructor(dto.getInstructor());
        course.setPrice(dto.getPrice());
        course.setQuota(dto.getQuota());

        Course added = courseRepository.save(course);
        return new CourseDTO(
                        added.getId(),
                        added.getName(),
                        added.getInstructor(),
                        added.getPrice(),
                        added.getQuota(),
                        added.getStudents().stream().map(Student::getId).collect(Collectors.toSet()) //i
        );

    }
    
    //update current course

    public CourseDTO updateCourse(Long id, CourseDTO dto) {
        Course existedCourse = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("This course not found with id: " + id));

        existedCourse.setName(dto.getName());
        existedCourse.setInstructor(dto.getInstructor());
        existedCourse.setPrice(dto.getPrice());
        existedCourse.setQuota(dto.getQuota());

        Course updated = courseRepository.save(existedCourse);
        return new CourseDTO(
                updated.getId(),
                updated.getName(),
                updated.getInstructor(),
                updated.getPrice(),
                updated.getQuota(),
                updated.getStudents().stream().map(Student::getId).collect(Collectors.toSet()) //i
);
    }

    //delete exist course

    public void deleteCourse(Long id) {
            Course existedCourse = courseRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("This course not found with id: " + id));

            for (Student student : existedCourse.getStudents()) { //i
                    student.getCourses().remove(existedCourse);
            }
            courseRepository.delete(existedCourse);
    }
    
    // add student to a course (öğrenciyi varolan kursa eklemek)
    public void addStudentToCourse(Long courseId, Long studentId) {
            Course course = courseRepository.findById(courseId).orElseThrow(
                            () -> new RuntimeException("Course not found with this id: " + courseId));
            Student student = studentRepository.findById(studentId).orElseThrow(
                            () -> new RuntimeException("Student not found with this id: " + studentId));

            course.getStudents().add(student);
            student.getCourses().add(course);

            courseRepository.save(course);
            studentRepository.save(student);
    }
    
    //Remove student from a course
    public void remoweStudentFromCourse(Long courseId, Long studentId) {
        Course course = courseRepository.findById(courseId).orElseThrow(
                            () -> new RuntimeException("Course not found with this id: " + courseId));
        Student student = studentRepository.findById(studentId).orElseThrow(
                            () -> new RuntimeException("Student not found with this id: " + studentId));
        
        course.getStudents().remove(student);
        student.getCourses().remove(course);

        courseRepository.save(course);
        studentRepository.save(student);
    }
}
