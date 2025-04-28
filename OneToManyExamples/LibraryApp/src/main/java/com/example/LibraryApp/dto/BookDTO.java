package com.example.LibraryApp.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private Long id;
    private String name;
    private Integer yearOfPublish;
    private Long authorId;
   
    public BookDTO(Long id, String name, Integer yearOfPublish, Long authorId) {
        this.id = id;
        this.name = name;
        this.yearOfPublish = yearOfPublish;
        this.authorId = authorId;
    }
   
}
