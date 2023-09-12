package com.tokmakov.hw06.service.impl;

import com.tokmakov.hw06.repository.AuthorRepository;
import com.tokmakov.hw06.domain.Author;
import com.tokmakov.hw06.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Author> findFirst(Author author) {
        return authorRepository.getByFirstAndLastNames(author.getFirstName(), author.getLastName());
    }

    @Override
    @Transactional
    public Author add(Author author) {
        return authorRepository.insert(author);
    }
}
