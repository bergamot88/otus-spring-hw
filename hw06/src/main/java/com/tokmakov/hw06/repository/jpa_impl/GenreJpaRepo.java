package com.tokmakov.hw06.repository.jpa_impl;

import com.tokmakov.hw06.domain.Genre;
import com.tokmakov.hw06.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreJpaRepo implements GenreRepository {
    @Override
    public Genre insert(Genre genre) {
        return null;
    }

    @Override
    public Optional<Genre> getByName(String name) {
        return Optional.empty();
    }
}
