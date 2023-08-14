package com.tokmakov.hw06.dao;

import com.tokmakov.hw06.domain.Genre;

import java.util.Optional;

public interface GenreDao {
    Genre insert(Genre genre);

    Optional<Genre> getByName(String name);
}
