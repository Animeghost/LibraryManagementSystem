package com.example.libraryManagementSystem.repository;

import com.example.libraryManagementSystem.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Books,Long> {

    @Query("""
            select b from Books b
            where upper(b.author) like upper(concat('%', ?1, '%')) or upper(b.title) like upper(concat('%', ?1, '%'))""")
    List<Books> findAllByAuthorOrTitleContainingSearchParam(String searchParameter);
}
