package com.System.School.dto;

import java.time.LocalDate;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class LectureDto {
    private Long lectureId;
    private String lectureName;
    private LocalDate lectureStartTime;
    
    private Set<Long> studentIds;
    private Long instructorId;
}
