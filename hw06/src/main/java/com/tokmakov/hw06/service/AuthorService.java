package com.tokmakov.hw06.service;

import com.tokmakov.hw06.domain.Author;

import java.util.Optional;

public interface AuthorService {
    Optional<Author> findFirst(Author author);

    Author add(Author author);
}
