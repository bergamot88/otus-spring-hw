package com.tokmakov.hw06.repository;

import com.tokmakov.hw06.domain.Book;
import com.tokmakov.hw06.exception.CollectionEmptyException;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    Book insert(Book book);

    void updateNameById(Long id, String newLabel);

    void deleteById(Long id);

    List<Book> getAll() throws CollectionEmptyException;

    Optional<Book> getById(Long id);

    Optional<Book> getByLabelAndAuthor(String label, String authorFirstName, String authorLastName);
}
