package com.tokmakov.hw06.repository;

import com.tokmakov.hw06.domain.Genre;

import java.util.Optional;

public interface GenreRepository {
    Genre insert(Genre genre);

    Optional<Genre> getByName(String name);
}
