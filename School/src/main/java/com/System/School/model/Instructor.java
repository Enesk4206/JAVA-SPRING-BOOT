package com.System.School.model;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter 
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long instructorId;
    private String instructorName;
    private String instructorSurname;
    private int instructorAge;
    private LocalDate instructorEntryTime;

    @OneToMany(mappedBy = "instructor")
    private Set<Lecture> lectures;
}
