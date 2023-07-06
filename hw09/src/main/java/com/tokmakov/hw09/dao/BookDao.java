package com.tokmakov.hw09.dao;

import com.tokmakov.hw09.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book insert(Book book);
    void updateNameById(Long id, String newLabel);
    void deleteById(Long id);
    List<Book> getAll();
    Optional<Book> getById(Long id);
    Optional<Book> getByLabelAndAuthor(String label, String authorFirstName, String authorLastName);
}
