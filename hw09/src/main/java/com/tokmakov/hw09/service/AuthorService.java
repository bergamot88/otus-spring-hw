package com.tokmakov.hw09.service;

import com.tokmakov.hw09.domain.Author;

import java.util.Optional;

public interface AuthorService {
    Optional<Author> findFirst(Author author);

    Author add(Author author);
}
