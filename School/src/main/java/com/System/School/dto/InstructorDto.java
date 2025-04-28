package com.System.School.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class InstructorDto {
    private Long instructorId;
    private String instructorName;
    private String instructorSurname;
    private int instructorAge;
    private LocalDate instructorEntryTime;
    private Set<Long> lectures;
}
