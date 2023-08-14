package com.tokmakov.hw05.dao;

import com.tokmakov.hw05.domain.Genre;

import java.util.Optional;

public interface GenreDao {
    Genre insert(Genre genre);

    Optional<Genre> getByName(String name);
}
