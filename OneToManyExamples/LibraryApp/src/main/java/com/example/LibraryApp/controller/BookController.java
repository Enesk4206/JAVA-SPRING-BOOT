package com.example.LibraryApp.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LibraryApp.dto.BookDTO;
import com.example.LibraryApp.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    private final BookService service;

    public BookController(BookService bookService) {
        service = bookService;
    }

    //add methode 
    @PostMapping
    public ResponseEntity<BookDTO> AddBook(@RequestBody BookDTO dto) {
        BookDTO addNewBook = service.addNewBook(dto);
        return ResponseEntity.ok(addNewBook);
    }
    //get by id methode

    @GetMapping("{id}")
    public ResponseEntity<BookDTO> GetByIdBook(@PathVariable Long id)
    {
        BookDTO getBookDTO = service.getByIdFromBook(id);
        return ResponseEntity.ok(getBookDTO);
    }
    //get all methode
    @GetMapping
    public ResponseEntity<List<BookDTO>> GetAllBooks() {
        List<BookDTO> books = service.getAllBooks();
        return ResponseEntity.ok(books);
    }

    //update methode
    @PutMapping("{id}")
    public ResponseEntity<BookDTO> UpdateBook(@PathVariable Long id, @RequestBody BookDTO dto) {
        BookDTO update = service.updateBook(id, dto);
        return ResponseEntity.ok(update);
    }
    @DeleteMapping("{id}")
    public ResponseEntity<Void> DeleteBook(@PathVariable Long id) {
        service.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
