package com.System.School.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.System.School.model.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    
}
