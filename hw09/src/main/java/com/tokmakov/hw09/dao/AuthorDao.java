package com.tokmakov.hw09.dao;

import com.tokmakov.hw09.domain.Author;

import java.util.Optional;

public interface AuthorDao {
    Optional<Author> getByFirstAndLastNames(String firstName, String lastName);

    Author insert(Author author);
}
