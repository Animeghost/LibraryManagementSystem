package com.example.libraryManagementSystem.service.impl;

import com.example.libraryManagementSystem.entity.Books;
import com.example.libraryManagementSystem.repository.BookRepository;
import com.example.libraryManagementSystem.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    @Override
    public List<Books> searchBooks(String parameter) {
        return bookRepository.findAllByAuthorOrTitleContainingSearchParam(parameter);
    }

    @Override
    public Books getBookById(Long bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(()-> new RuntimeException("Book Not Found At This ID: "+bookId));
    }

    @Override
    public List<Books> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * @return Books
     */
    @Override
    public Books addBook(Books book) {
        bookRepository.save(book);
        return book;
    }

    /**
     * @return Books
     */
    @Override
    public Books updateBook(Books updatedBook,Long bookId) {
        Books existingBook = getBookById(bookId);

        if (!updatedBook.getAuthor().isEmpty()){
            existingBook.setAuthor(updatedBook.getAuthor());
        }
        if (!updatedBook.getIsbn().isEmpty()){
            existingBook.setIsbn(updatedBook.getIsbn());
        }
        if (!updatedBook.getTitle().isEmpty()){
            existingBook.setTitle(updatedBook.getTitle());
        }
        if (!updatedBook.getPublishedDate().isEmpty()){
            existingBook.setPublishedDate(updatedBook.getPublishedDate());
        }

        bookRepository.save(existingBook);
        return existingBook;

    }

    /**
     * @return
     * String success message
     */
    @Override
    public String deleteBook(Long bookId) {
        bookRepository.deleteById(bookId);
        return "Successfully Deleted";
    }
}
