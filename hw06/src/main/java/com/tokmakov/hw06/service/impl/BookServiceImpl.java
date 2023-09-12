package com.tokmakov.hw06.service.impl;

import com.tokmakov.hw06.repository.BookRepository;
import com.tokmakov.hw06.domain.Author;
import com.tokmakov.hw06.domain.Book;
import com.tokmakov.hw06.domain.Genre;
import com.tokmakov.hw06.exception.CollectionEmptyException;
import com.tokmakov.hw06.service.AuthorService;
import com.tokmakov.hw06.service.BookService;
import com.tokmakov.hw06.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    private final AuthorService authorService;

    private final GenreService genreService;

    @Override
    @Transactional
    public Book add(Book book) {
        Author author = authorService.findFirst(book.getAuthor())
                                     .orElse(authorService.add(book.getAuthor()));

        Genre genre = genreService.findFirst(book.getGenre())
                                  .orElse(genreService.add(book.getGenre()));

        Book newBook = new Book(book.getLabel(), author, genre);

        return bookRepository.insert(newBook);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Book book = findById(id).orElseThrow();
        bookRepository.remove(book);
    }

    @Override
    @Transactional
    public Book updateBookLabelById(Long id, String newLabel) throws EntityNotFoundException {
        Book book = findById(id).orElseThrow();
        book.setLabel(newLabel);
        return bookRepository.update(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> findAll() throws CollectionEmptyException {
        return bookRepository.getAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findByLabelAndAuthor(String label, String authorFirstName, String authorLastName) {
        return bookRepository.getByLabelAndAuthor(label, authorFirstName, authorLastName);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findById(Long id) {
        return bookRepository.getById(id);
    }
}
