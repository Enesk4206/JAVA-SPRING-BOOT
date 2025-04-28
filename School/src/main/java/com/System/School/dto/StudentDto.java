package com.System.School.dto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentDto {
    private Long studentId;
    private String studentName;
    private String studentSurname;
    private Integer studentAge;
    private String studentEmail;
    private Set<Long> lectures;
}
