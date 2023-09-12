package com.tokmakov.hw06.repository.jpa_impl;

import com.tokmakov.hw06.domain.Genre;
import com.tokmakov.hw06.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreJpaRepo implements GenreRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Genre insert(Genre genre) {
        entityManager.persist(genre);
        return genre;
    }

    @Override
    public Optional<Genre> getByName(String name) {
        return Optional.ofNullable(entityManager.find(Genre.class, name));
    }
}
