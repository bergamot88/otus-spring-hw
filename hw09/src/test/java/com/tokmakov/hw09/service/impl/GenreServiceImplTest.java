package com.tokmakov.hw09.service.impl;

import com.tokmakov.hw09.dao.GenreDao;
import com.tokmakov.hw09.domain.Genre;
import com.tokmakov.hw09.service.GenreService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = GenreServiceImpl.class)
public class GenreServiceImplTest {

    @MockBean
    private GenreDao genreDao;

    @Autowired
    private GenreService genreService;

    private static final String EXIST_GENRE_NAME = "Philosophy";
    private static final Long EXIST_GENRE_ID = 1L;
    private static final String NEW_GENRE_NAME = "Thriller";

    @Test
    void testFindFirst() {
        Genre expectedGenre = new Genre(EXIST_GENRE_ID, EXIST_GENRE_NAME);

        when(genreDao.getByName(expectedGenre.getName())).thenReturn(Optional.of(expectedGenre));

        Optional<Genre> genre = genreService.findFirst(expectedGenre);

        assertThat(genre, not(Optional.empty()));
        assertThat(genre.get(), equalTo(expectedGenre));
    }

    @Test
    void testAdd() {
        Genre newGenre = new Genre(NEW_GENRE_NAME);

        when(genreDao.insert(newGenre)).thenReturn(newGenre);

        newGenre = genreService.add(newGenre);

        when(genreDao.getByName(newGenre.getName())).thenReturn(Optional.of(newGenre));

        Optional<Genre> genreOptional = genreService.findFirst(newGenre);

        assertThat(genreOptional, not(Optional.empty()));
        assertThat(genreOptional.get(), equalTo(newGenre));
    }
}
