package com.tokmakov.hw09.dao;

import com.tokmakov.hw09.domain.Genre;

import java.util.Optional;

public interface GenreDao {
    Genre insert(Genre genre);

    Optional<Genre> getByName(String name);
}
