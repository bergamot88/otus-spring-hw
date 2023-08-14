package com.tokmakov.hw05.dao;

import com.tokmakov.hw05.domain.Book;
import com.tokmakov.hw05.exception.CollectionEmptyException;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book insert(Book book);

    void updateNameById(Long id, String newLabel);

    void deleteById(Long id);

    List<Book> getAll() throws CollectionEmptyException;

    Optional<Book> getById(Long id);

    Optional<Book> getByLabelAndAuthor(String label, String authorFirstName, String authorLastName);
}
