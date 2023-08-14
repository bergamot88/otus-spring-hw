package com.tokmakov.hw05.shell;

import com.tokmakov.hw05.domain.Author;
import com.tokmakov.hw05.domain.Book;
import com.tokmakov.hw05.domain.Genre;
import com.tokmakov.hw05.exception.CollectionEmptyException;
import com.tokmakov.hw05.service.BookConverterService;
import com.tokmakov.hw05.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.List;
import java.util.NoSuchElementException;

@ShellComponent
@RequiredArgsConstructor
public class AppCommands {
    private final BookService bookService;

    private final BookConverterService<String> bookConverter;

    @ShellMethod(value = "Add book", key = "add_book")
    public String addBook(@ShellOption("book_label") String bookLabel,
                          @ShellOption("author_first_name") String authorFirstName,
                          @ShellOption("author_last_name") String authorLastName,
                          @ShellOption("genre_name") String genreName) {
        Author author = new Author(authorFirstName, authorLastName);
        Genre genre = new Genre(genreName);
        Book book = new Book(bookLabel, author, genre);
        book = bookService.add(book);
        return "You add new book: " + bookConverter.convert(book);
    }

    @ShellMethod(value = "Update book label", key = "update_book_label")
    public String updateBookLabel(@ShellOption("id") Long id,
                                  @ShellOption("new_label") String newLabel) {
        bookService.updateBookNameById(id, newLabel);
        return "Book label was updated";
    }

    @ShellMethod(value = "Get books list", key = "get_books_list")
    public String getBooksList() {
        List<Book> books;
        try {
            books = bookService.findAll();
        } catch (CollectionEmptyException e) {
            return "Library is empty";
        }
        return bookConverter.convert(books);
    }

    @ShellMethod(value = "Get book by id", key = "get_book_by_id")
    public String getBookById(@ShellOption("book_id") Long id) {
        Book book;
        try {
            book = bookService.findById(id).orElseThrow();
        } catch (NoSuchElementException exception) {
            return String.format("Book with id %s not found", id);
        }
        return bookConverter.convert(book);
    }

    @ShellMethod(value = "Delete book with label and author", key = "delete_book_label_and_author")
    public String deleteBookWithLabelAndAuthor(@ShellOption("book_label") String bookLabel,
                                             @ShellOption("author_first_name") String authorFirstName,
                                             @ShellOption("author_last_name") String authorLastName) {
        Book book = new Book(bookLabel, new Author(authorFirstName, authorLastName));
        try {
            book = bookService.findByLabelAndAuthor(bookLabel, authorFirstName, authorLastName)
                              .orElseThrow();
        } catch (NoSuchElementException exception) {
            return String.format("Book %s not found. There's nothing to delete", bookConverter.convert(book));
        }
        bookService.deleteById(book.getId());
        return String.format("Book %s has been deleted", bookConverter.convert(book));
    }
}
