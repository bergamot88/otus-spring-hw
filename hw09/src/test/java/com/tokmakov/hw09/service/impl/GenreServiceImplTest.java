package com.tokmakov.hw09.service.impl;

import com.tokmakov.hw09.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@SpringBootTest
public class GenreServiceImplTest {
    @Autowired
    GenreServiceImpl genreService;

    private static final String EXIST_GENRE_NAME = "Philosophy";
    private static final Long EXIST_GENRE_ID = 1L;
    private static final String NEW_GENRE_NAME = "Thriller";

    @Test
    void testFindFirst() {
        Genre expectedGenre = new Genre(EXIST_GENRE_ID, EXIST_GENRE_NAME);
        Optional<Genre> genre = genreService.findFirst(expectedGenre);
        assertThat(genre, not(Optional.empty()));
        assertThat(genre.get(), equalTo(expectedGenre));
    }

    @Test
    void testAdd() {
        Genre newGenre = new Genre(NEW_GENRE_NAME);
        newGenre = genreService.add(newGenre);
        Optional<Genre> genreOptional = genreService.findFirst(newGenre);
        assertThat(genreOptional, not(Optional.empty()));
        assertThat(genreOptional.get(), equalTo(newGenre));
    }
}
