package com.example.libraryManagementSystem.service;

import com.example.libraryManagementSystem.entity.Books;

import java.util.List;


public interface BookService {
    List<Books> searchBooks(String parameter);
    Books getBookById(Long id);
    List<Books> getAllBooks();
    Books addBook(Books book);
    Books updateBook(Books book,Long bookId);
    String deleteBook(Long bookId);
}
