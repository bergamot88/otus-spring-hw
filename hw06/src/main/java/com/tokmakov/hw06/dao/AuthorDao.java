package com.tokmakov.hw06.dao;

import com.tokmakov.hw06.domain.Author;

import java.util.Optional;

public interface AuthorDao {
    Optional<Author> getByFirstAndLastNames(String firstName, String lastName);

    Author insert(Author author);
}
