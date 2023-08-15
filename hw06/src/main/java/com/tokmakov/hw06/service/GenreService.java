package com.tokmakov.hw06.service;

import com.tokmakov.hw06.domain.Genre;

import java.util.Optional;

public interface GenreService {
    Optional<Genre> findFirst(Genre genre);

    Genre add(Genre genre);
}
