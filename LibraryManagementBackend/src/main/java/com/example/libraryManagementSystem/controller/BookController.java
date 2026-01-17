package com.example.libraryManagementSystem.controller;

import com.example.libraryManagementSystem.entity.Books;
import com.example.libraryManagementSystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;

    @GetMapping("/")
    public ResponseEntity<List<Books>> getAllBooks(){

        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<Books> getBookById(@PathVariable("bookId") Long bookId){
        return new ResponseEntity<>(bookService.getBookById(bookId),HttpStatus.OK);
    }

    @GetMapping("/search/")
    public ResponseEntity<List<Books>> getBookBySearch(@Param("name") String searchParameter){
        return new ResponseEntity<>(bookService.searchBooks(searchParameter),HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Books> addNewBook(@RequestBody Books newBook){
        return new ResponseEntity<>(bookService.addBook(newBook),HttpStatus.CREATED);
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Books> updateBook(@RequestBody Books updatedBook, @PathVariable("bookId") Long bookId){
        return new ResponseEntity<>(bookService.updateBook(updatedBook,bookId),HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity deleteBook(@PathVariable("bookId") Long bookId){
        return new ResponseEntity(bookService.deleteBook(bookId),HttpStatus.OK);
    }
}
