package com.example.librarymanagementfrontend.service;

import com.example.librarymanagementfrontend.http.HttpClient;
import com.example.librarymanagementfrontend.http.HttpResponse;
import com.example.librarymanagementfrontend.http.JsonUtil;
import com.example.librarymanagementfrontend.model.Book;

import java.io.IOException;
import java.util.List;

import static com.example.librarymanagementfrontend.http.JsonUtil.fromJsonList;


public class BookService {
    private final HttpClient httpClient;

    public BookService(String baseUrl) {
        this.httpClient = new HttpClient(baseUrl);
    }


    public  List<Book> getAllBooks() throws IOException {
        HttpResponse response = httpClient.get("books/");

        if (response.isSuccess()) {
            return fromJsonList(response.getBody(), Book.class);
        } else {
            throw new IOException("Failed to get books: " + response.getStatusCode());
        }
    }

    public Book getBookById(Long bookId) throws IOException {
        HttpResponse response = httpClient.get("books/" + bookId);

        if (response.isSuccess()) {
            return JsonUtil.fromJson(response.getBody(), Book.class);
        } else {
            throw new IOException("Failed to get book "+bookId+": " + response.getStatusCode());
        }
    }

    public Book createBook(Book book) throws IOException {
        String jsonBody = JsonUtil.toJson(book);
        HttpResponse response = httpClient.post("books/", jsonBody);

        if (response.isSuccess()) {
            return JsonUtil.fromJson(response.getBody(), Book.class);
        } else {
            throw new IOException("Failed to create book: " + response.getStatusCode());
        }
    }

    public Book updateBook(Long bookId, Book book) throws IOException {
        String jsonBody = JsonUtil.toJson(book);
        HttpResponse response = httpClient.put("books/" + bookId, jsonBody);

        if (response.isSuccess()) {
            return JsonUtil.fromJson(response.getBody(), Book.class);
        } else {
            throw new IOException("Failed to update book: " + response.getStatusCode());

        }
    }

    public boolean deleteBook(Long bookId) throws IOException {
        HttpResponse response = httpClient.delete("books/" + bookId);
        return response.isSuccess();
    }
}
