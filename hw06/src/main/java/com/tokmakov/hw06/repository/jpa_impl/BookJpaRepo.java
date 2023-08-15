package com.tokmakov.hw06.repository.jpa_impl;

import com.tokmakov.hw06.domain.Book;
import com.tokmakov.hw06.exception.CollectionEmptyException;
import com.tokmakov.hw06.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookJpaRepo implements BookRepository {
    @Override
    public Book insert(Book book) {
        return null;
    }

    @Override
    public void updateNameById(Long id, String newLabel) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public List<Book> getAll() throws CollectionEmptyException {
        return null;
    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Book> getByLabelAndAuthor(String label, String authorFirstName, String authorLastName) {
        return Optional.empty();
    }
}
