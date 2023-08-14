package com.tokmakov.hw06.service.impl;

import com.tokmakov.hw06.dao.AuthorDao;
import com.tokmakov.hw06.domain.Author;
import com.tokmakov.hw06.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public Optional<Author> findFirst(Author author) {
        return authorDao.getByFirstAndLastNames(author.getFirstName(), author.getLastName());
    }

    @Override
    public Author add(Author author) {
        return authorDao.insert(author);
    }
}
