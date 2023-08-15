package com.tokmakov.hw06.repository;

import com.tokmakov.hw06.domain.Author;

import java.util.Optional;

public interface AuthorRepository {
    Optional<Author> getByFirstAndLastNames(String firstName, String lastName);

    Author insert(Author author);
}
