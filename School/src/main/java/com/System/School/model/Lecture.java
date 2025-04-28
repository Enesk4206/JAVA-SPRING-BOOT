package com.System.School.model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;
    private String lectureName;
    private LocalDate lectureStartTime;

    @ManyToMany
    @JoinTable(
        name = "lecture_student",
        joinColumns = @JoinColumn(name= "lecture_id"),
        inverseJoinColumns = @JoinColumn(name= "student_id")
    )
    private Set<Student> students;

    @ManyToOne
    @JoinColumn(name =  "instructor_id", nullable = true)
    private Instructor instructor;

}
