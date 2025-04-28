package com.System.School.service;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.System.School.dto.InstructorDto;
import com.System.School.model.Instructor;
import com.System.School.model.Lecture;
import com.System.School.repository.InstructorRepository;

@Service
public class InstructorService {
    
    @Autowired
    private InstructorRepository instructorRepository;

    

    // add instructor
    public InstructorDto AddInstructor(InstructorDto dto) {

        Instructor instructor = new Instructor();

        instructor.setInstructorName(dto.getInstructorName());
        instructor.setInstructorSurname(dto.getInstructorSurname());
        instructor.setInstructorAge(dto.getInstructorAge());
        instructor.setInstructorEntryTime(dto.getInstructorEntryTime());
        instructor.setLectures(new HashSet<>());

        Instructor created = instructorRepository.save(instructor);

        return new InstructorDto(
                created.getInstructorId(),
                created.getInstructorName(),
                created.getInstructorSurname(),
                created.getInstructorAge(),
                created.getInstructorEntryTime(),
                created.getLectures().stream().map(Lecture::getLectureId).collect(Collectors.toSet())

        );
    }
    
    //get all instructor

    public List<InstructorDto> getAllInstructors() {
        List<Instructor> instructors = instructorRepository.findAll();

        return instructors.stream().map(instructor -> new InstructorDto(
                instructor.getInstructorId(),
                instructor.getInstructorName(),
                instructor.getInstructorSurname(),
                instructor.getInstructorAge(),
                instructor.getInstructorEntryTime(),
                instructor.getLectures().stream().map(Lecture::getLectureId).collect(Collectors.toSet())))
                .collect(Collectors.toList());
    }
    
    //get by id Instructor

    public InstructorDto getByIdInstructor(Long id) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Eğitmen bulunamadı id:" + id));
        return new InstructorDto(
                instructor.getInstructorId(),
                instructor.getInstructorName(),
                instructor.getInstructorSurname(),
                instructor.getInstructorAge(),
                instructor.getInstructorEntryTime(),
                instructor.getLectures().stream().map(Lecture::getLectureId).collect(Collectors.toSet())

        );
    }
    
    //update instructor

    public InstructorDto updateInstructor(Long id, InstructorDto dto) {
        Instructor instructor = instructorRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Eğitmen bulunamadı id:" + id));

        instructor.setInstructorName(dto.getInstructorName());
        instructor.setInstructorSurname(dto.getInstructorSurname());
        instructor.setInstructorAge(dto.getInstructorAge());
        instructor.setInstructorEntryTime(dto.getInstructorEntryTime());

        Instructor updated = instructorRepository.save(instructor);
        return new InstructorDto(
                updated.getInstructorId(),
                updated.getInstructorName(),
                updated.getInstructorSurname(),
                updated.getInstructorAge(),
                updated.getInstructorEntryTime(),
                updated.getLectures().stream().map(Lecture::getLectureId).collect(Collectors.toSet())

        );

    }
    
    // delete instructor

    public void deleteInstructor(Long id) {
        Instructor existInstuctor = instructorRepository.findById(id).orElseThrow(
            ()-> new RuntimeException("Eğitmen bulunamadı id: "+id)
        );

        //sınıflardan eğitmeni silmek
        for (Lecture lecture : existInstuctor.getLectures()) {
            lecture.setInstructor(null);
        }
       
        instructorRepository.delete(existInstuctor);
        
    }
}
