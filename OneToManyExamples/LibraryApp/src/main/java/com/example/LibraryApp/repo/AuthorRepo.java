package com.example.LibraryApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LibraryApp.model.Author;

public interface AuthorRepo extends JpaRepository<Author, Long> {
    
}
