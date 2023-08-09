package com.tokmakov.hw09.service;

import com.tokmakov.hw09.domain.Genre;

import java.util.Optional;

public interface GenreService {
    Optional<Genre> findFirst(Genre genre);

    Genre add(Genre genre);
}
