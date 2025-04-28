package com.example.LibraryApp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LibraryApp.model.Book;

public interface BookRepo extends JpaRepository<Book, Long> {

}
