package com.tokmakov.hw09.dao.jdbc_impl;

import com.tokmakov.hw09.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@JdbcTest
@Import(GenreDaoJdbc.class)
public class GenreDaoJdbcTest {

    @Autowired
    private GenreDaoJdbc genreDaoJdbc;

    private static final String EXIST_GENRE_NAME = "Philosophy";
    private static final Long EXIST_GENRE_ID = 1L;
    private static final String NEW_GENRE_NAME = "Thriller";

    @Test
    void testInsert() {
        Genre genre = new Genre(NEW_GENRE_NAME);
        genre = genreDaoJdbc.insert(genre);

        Genre genreFromDB = genreDaoJdbc.getByName(genre.getName())
                .orElseThrow();

        assertThat(genreFromDB, equalTo(genre));
    }

    @Test
    void testGetByName() {
        Optional<Genre> genre = genreDaoJdbc.getByName(EXIST_GENRE_NAME);
        Genre expectedGenre = new Genre(EXIST_GENRE_ID, EXIST_GENRE_NAME);
        assertThat(genre, not(Optional.empty()));
        assertThat(genre.get(), equalTo(expectedGenre));
    }
}
