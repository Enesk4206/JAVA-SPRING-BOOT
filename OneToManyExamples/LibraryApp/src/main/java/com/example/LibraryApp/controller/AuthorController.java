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

import com.example.LibraryApp.dto.AuthorDTO;
import com.example.LibraryApp.service.AuthorService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService service;

    public AuthorController(AuthorService authorService) {
        service = authorService;
    }

    //add methode
    @PostMapping
    public ResponseEntity<AuthorDTO> AddNewBook(@RequestBody AuthorDTO dto) {
        AuthorDTO newAuthor = service.addNewAuthor(dto);
        return ResponseEntity.ok(newAuthor);
    }
    //getById methode
    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> GetByIdAuthor(@PathVariable Long id) {
        AuthorDTO getAuthor = service.getByIdFromAuthor(id);
        return ResponseEntity.ok(getAuthor);
    }

    //getall methode
    @GetMapping
    public ResponseEntity<List<AuthorDTO>> GetAllAuthors() {
        List<AuthorDTO> authors = service.getAllDataFromAuthor();
        return ResponseEntity.ok(authors);
    }

    //update methode
    @PutMapping("{id}") 
    public ResponseEntity<AuthorDTO> UpdateAuthor(@PathVariable Long id, @RequestBody AuthorDTO dto) {
        AuthorDTO updateAuthor = service.updateAuthor(id, dto);
        return ResponseEntity.ok(updateAuthor);
    }

    //delete
    @DeleteMapping("{id}")
    public ResponseEntity<AuthorDTO> DeleteAuthor(@PathVariable Long id) {
        service.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }

}
