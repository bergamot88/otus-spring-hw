package com.tokmakov.hw09.service;

import com.tokmakov.hw09.domain.Book;
import com.tokmakov.hw09.exception.CollectionEmptyException;

import java.util.List;
import java.util.Optional;

public interface BookService {
    Book add(Book book);

    void deleteById(Long id);

    void updateBookNameById(Long id, String newName);

    List<Book> findAll() throws CollectionEmptyException;

    Optional<Book> findByLabelAndAuthor(String label, String authorFirstName, String authorLastName);

    Optional<Book> findById(Long id);
}
