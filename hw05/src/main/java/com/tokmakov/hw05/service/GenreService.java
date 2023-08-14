package com.tokmakov.hw05.service;

import com.tokmakov.hw05.domain.Genre;

import java.util.Optional;

public interface GenreService {
    Optional<Genre> findFirst(Genre genre);

    Genre add(Genre genre);
}
