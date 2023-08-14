package com.tokmakov.hw06.service.impl;

import com.tokmakov.hw06.dao.GenreDao;
import com.tokmakov.hw06.domain.Genre;
import com.tokmakov.hw06.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    public Optional<Genre> findFirst(Genre genre) {
        return genreDao.getByName(genre.getName());
    }

    @Override
    public Genre add(Genre genre) {
        return genreDao.insert(genre);
    }
}
