package com.example.LibraryApp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.LibraryApp.dto.BookDTO;
import com.example.LibraryApp.model.Author;
import com.example.LibraryApp.model.Book;
import com.example.LibraryApp.repo.AuthorRepo;
import com.example.LibraryApp.repo.BookRepo;

@Service
public class BookService {

    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    public BookService(BookRepo bookRepo, AuthorRepo authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }
    
    //add
    public BookDTO addNewBook(BookDTO dto) {
        Book book = new Book();
        book.setName(dto.getName());
        book.setYearOfPublish(dto.getYearOfPublish());

        //find Author
        Author author = authorRepo.findById(dto.getAuthorId()).orElseThrow(
                () -> new RuntimeException("Author not found with id: " + dto.getAuthorId()));
        book.setAuthor(author);
        Book added = bookRepo.save(book);
        return new BookDTO(added.getId(), added.getName(), added.getYearOfPublish(), added.getAuthor().getId());
    }
    
    //getall
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepo.findAll();
        return books.stream().map(book -> new BookDTO(
                book.getId(),
                book.getName(),
                book.getYearOfPublish(),
                book.getAuthor().getId() !=null ? book.getAuthor().getId    () : null )) //sadece burada bana isim getirsin
                .collect(Collectors.toList());
    }
    
    //get by id

    public BookDTO getByIdFromBook(Long id) {
        Book book = bookRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found with id: " + id));
        return new BookDTO(
                book.getId(),
                book.getName(),
                book.getYearOfPublish(),
                book.getAuthor().getId() != null ? book.getAuthor().getId() : null
        );
    }
    
    // update
    public BookDTO updateBook(Long id, BookDTO dto) {
        Book book = bookRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Book not found with id: " + id));

        book.setName(dto.getName());
        book.setYearOfPublish(dto.getYearOfPublish());
        Author author = authorRepo.findById(dto.getAuthorId()).orElseThrow(
                () -> new RuntimeException("Author not found with id: " + dto.getAuthorId()));
        book.setAuthor(author);

        Book updated = bookRepo.save(book);
        return new BookDTO(updated.getId(), updated.getName(), updated.getYearOfPublish(), updated.getAuthor().getId());
    }
    
    //delete
    public void deleteBook(Long id) {
        Book book = bookRepo.findById(id).orElseThrow(
            ()-> new RuntimeException("Book not found with id: "+id)
        );
        bookRepo.delete(book);
    }
}
