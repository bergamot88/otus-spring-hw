package com.tokmakov.hw05.service;

import com.tokmakov.hw05.domain.Author;

import java.util.Optional;

public interface AuthorService {
    Optional<Author> findFirst(Author author);

    Author add(Author author);
}
