package com.tokmakov.hw06.dao.jdbc_impl;

import com.tokmakov.hw06.domain.Author;
import com.tokmakov.hw06.domain.Book;
import com.tokmakov.hw06.domain.Genre;
import com.tokmakov.hw06.repository.jdbc_impl.BookDaoJdbc;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@JdbcTest
@Import(BookDaoJdbc.class)
public class BookRepositoryJdbcTest {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    private static final Author EXIST_AUTHOR =
            new Author(1L, "Alexander", "Zinoviev");
    private static final Genre EXIST_GENRE =
            new Genre(1L, "Philosophy");

    private static final Long EXIST_BOOK_ID = 1L;

    private static final String EXIST_BOOK_LABEL = "The Radiant Future";

    private static final String NEW_BOOK_LABEL = "new book";

    @Test
    void testInsert() {
        Book book = new Book(NEW_BOOK_LABEL, EXIST_AUTHOR, EXIST_GENRE);
        book = bookDaoJdbc.insert(book);
        Book bookFromDB = bookDaoJdbc.getById(book.getId()).orElseThrow();
        assertThat(bookFromDB, equalTo(book));
    }

    @Test
    void testUpdateNameById() {
        bookDaoJdbc.updateNameById(EXIST_BOOK_ID, NEW_BOOK_LABEL);
        Book book = bookDaoJdbc.getById(EXIST_BOOK_ID).orElseThrow();
        assertThat(book.getLabel(), equalTo(NEW_BOOK_LABEL));
    }

    @Test
    void testDeleteById() {
        bookDaoJdbc.deleteById(EXIST_BOOK_ID);
        Optional<Book> book = bookDaoJdbc.getById(EXIST_BOOK_ID);
        assertThat(book, equalTo(Optional.empty()));
    }

    @Test
    void testGetAll() {
        List<Book> expectedBooks = List.of(
                new Book(EXIST_BOOK_ID, EXIST_BOOK_LABEL, EXIST_AUTHOR, EXIST_GENRE));
        List<Book> books = bookDaoJdbc.getAll();
        assertThat(books, equalTo(expectedBooks));
    }

    @Test
    void testGetById() {
        Book expectedBook =
                new Book(EXIST_BOOK_ID, EXIST_BOOK_LABEL, EXIST_AUTHOR, EXIST_GENRE);
        Optional<Book> book = bookDaoJdbc.getById(expectedBook.getId());
        assertThat(book, not(Optional.empty()));
        assertThat(book.get(), equalTo(expectedBook));
    }

    @Test
    void testGetByLabelAndAuthor() {
        Book expectedBook =
                new Book(EXIST_BOOK_ID, EXIST_BOOK_LABEL, EXIST_AUTHOR, EXIST_GENRE);
        Optional<Book> book =
                bookDaoJdbc.getByLabelAndAuthor(expectedBook.getLabel(),
                                                expectedBook.getAuthor().getFirstName(),
                                                expectedBook.getAuthor().getLastName());
        assertThat(book, not(Optional.empty()));
        assertThat(book.get(), equalTo(expectedBook));
    }
}
