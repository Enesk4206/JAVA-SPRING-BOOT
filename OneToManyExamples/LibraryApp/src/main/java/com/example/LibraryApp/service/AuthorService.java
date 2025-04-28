package com.example.LibraryApp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.LibraryApp.dto.AuthorDTO;
import com.example.LibraryApp.model.Author;
import com.example.LibraryApp.repo.AuthorRepo;

@Service
public class AuthorService {
    
    // @Autowired
    // private AuthorRepo authorRepo;

    private final AuthorRepo authorRepo;

    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    //add 
    public AuthorDTO addNewAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setName(authorDTO.getName());
        author.setSurname(authorDTO.getSurname());
        author.setAge(authorDTO.getAge());

        Author saved = authorRepo.save(author);
        return new AuthorDTO(saved.getId(), saved.getName(), saved.getSurname(), saved.getAge());
    }
    
    //getall

    public List<AuthorDTO> getAllDataFromAuthor() {
        List<Author> authors = authorRepo.findAll();
        return authors.stream().map(author -> new AuthorDTO(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getAge())).collect(Collectors.toList());
    }
    
    //getById

    public AuthorDTO getByIdFromAuthor(Long id) {
        Author author = authorRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Author not found with: " + id));
        return new AuthorDTO(author.getId(), author.getName(), author.getSurname(), author.getAge());
    }
    
    // update 
    public AuthorDTO updateAuthor(Long id, AuthorDTO dto) {
        Author author = authorRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Author not found with: " + id));
        author.setName(dto.getName());
        author.setSurname(dto.getSurname());
        author.setAge(dto.getAge());

        Author updated = authorRepo.save(author);
        return new AuthorDTO(updated.getId(), updated.getName(), updated.getSurname(), updated.getAge());
    }
    
    //delete
    public void deleteAuthor(Long id) {
        Author author = authorRepo.findById(id).orElseThrow(
            ()-> new RuntimeException("Author not found with"+id)
        );
        authorRepo.delete(author);
    }

}
