package com.tokmakov.hw05.service.impl;

import com.tokmakov.hw05.dao.BookDao;
import com.tokmakov.hw05.domain.Author;
import com.tokmakov.hw05.domain.Book;
import com.tokmakov.hw05.domain.Genre;
import com.tokmakov.hw05.exception.CollectionEmptyException;
import com.tokmakov.hw05.service.AuthorService;
import com.tokmakov.hw05.service.BookService;
import com.tokmakov.hw05.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;

    private final AuthorService authorService;

    private final GenreService genreService;

    @Override
    public Book add(Book book) {
        Author author = authorService.findFirst(book.getAuthor())
                                     .orElse(authorService.add(book.getAuthor()));

        Genre genre = genreService.findFirst(book.getGenre())
                                  .orElse(genreService.add(book.getGenre()));

        Book newBook = new Book(book.getLabel(), author, genre);

        return bookDao.insert(newBook);
    }

    @Override
    public void deleteById(Long id) {
        bookDao.deleteById(id);
    }

    @Override
    public void updateBookNameById(Long id, String newLabel) {
        bookDao.updateNameById(id, newLabel);
    }

    @Override
    public List<Book> findAll() throws CollectionEmptyException {
        return bookDao.getAll();
    }

    @Override
    public Optional<Book> findByLabelAndAuthor(String label, String authorFirstName, String authorLastName) {
        return bookDao.getByLabelAndAuthor(label, authorFirstName, authorLastName);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookDao.getById(id);
    }
}
