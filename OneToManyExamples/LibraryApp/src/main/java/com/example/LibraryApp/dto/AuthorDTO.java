package com.example.LibraryApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDTO {
    
    private Long id;
    private String name;
    private String surname;
    private Integer age;

    public AuthorDTO(Long id, String name, String surname, Integer age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }
}
