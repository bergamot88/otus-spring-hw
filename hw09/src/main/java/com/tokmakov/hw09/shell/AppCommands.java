package com.tokmakov.hw09.shell;

import com.tokmakov.hw09.domain.Author;
import com.tokmakov.hw09.domain.Book;
import com.tokmakov.hw09.domain.Genre;
import com.tokmakov.hw09.exception.CollectionEmptyException;
import com.tokmakov.hw09.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.NoSuchElementException;

@ShellComponent
@RequiredArgsConstructor
public class AppCommands {
    private final BookService bookService;

    @ShellMethod(value = "Add book", key = "add_book")
    public String addBook(@ShellOption("book_label") String bookLabel,
                          @ShellOption("author_first_name") String authorFirstName,
                          @ShellOption("author_last_name") String authorLastName,
                          @ShellOption("genre_name") String genreName) {
        Author author = new Author(authorFirstName, authorLastName);
        Genre genre = new Genre(genreName);
        Book book = new Book(bookLabel, author, genre);
        book = bookService.add(book);
        return String.format("You add new book: %s", getFormattedBookInfo(book));
    }

    @ShellMethod(value = "Update book label", key = "update_book_label")
    public String updateBookLabel(@ShellOption("id") Long id,
                                  @ShellOption("new_label") String newLabel) {
        bookService.updateBookNameById(id, newLabel);
        return "Book label was updated";
    }

    @ShellMethod(value = "Get books list", key = "get_books_list")
    public String getBooksList() {
        StringBuffer stringBuffer = new StringBuffer();
        try {
            bookService.findAll().forEach(book -> stringBuffer.append(getFormattedBookInfo(book)).append("\n"));
        } catch (CollectionEmptyException e) {
            return "Library is empty";
        }
        return stringBuffer.toString();
    }

    @ShellMethod(value = "Get book by id", key = "get_book_by_id")
    public String getBookById(@ShellOption("book_id") Long id) {
        Book book;
        try {
            book = bookService.findById(id).orElseThrow();
        } catch (NoSuchElementException exception) {
            return String.format("Book with id %s not found", id);
        }
        return getFormattedBookInfo(book);
    }

    @ShellMethod(value = "Delete book with label and author", key = "delete_book_label_and_author")
    public String deleteBookWithLabelAndAuthor(@ShellOption("book_label") String bookLabel,
                                             @ShellOption("author_first_name") String authorFirstName,
                                             @ShellOption("author_last_name") String authorLastName) {
        Book book;
        try {
            book = bookService.findByLabelAndAuthor(bookLabel, authorFirstName, authorLastName)
                              .orElseThrow();
        } catch (NoSuchElementException exception) {
            return String.format("Book '%s' - %s %s not found. There's nothing to delete",
                    bookLabel, authorFirstName, authorLastName);
        }
        bookService.deleteById(book.getId());
        return String.format("Book %s has been deleted", getFormattedBookInfo(book));
    }

    private String getFormattedBookInfo(Book book) {
        return String.format("'%s' - %s %s", book.getLabel(), book.getAuthor().getFirstName(),
                book.getAuthor().getLastName());
    }
}
