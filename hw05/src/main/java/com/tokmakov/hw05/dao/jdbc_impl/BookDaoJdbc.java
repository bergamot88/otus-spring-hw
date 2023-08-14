package com.tokmakov.hw05.dao.jdbc_impl;

import com.tokmakov.hw05.dao.BookDao;
import com.tokmakov.hw05.domain.Author;
import com.tokmakov.hw05.domain.Book;
import com.tokmakov.hw05.domain.Genre;
import com.tokmakov.hw05.exception.CollectionEmptyException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Book insert(Book book) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("label", book.getLabel())
                .addValue("author_id", book.getAuthor().getId())
                .addValue("genre_id", book.getGenre().getId());
        namedParameterJdbcTemplate.update("INSERT INTO book (label, author_id, genre_id) " +
                                              "VALUES (:label, :author_id, :genre_id)", params, keyHolder);
        return new Book(keyHolder.getKey().longValue(), book.getLabel(), book.getAuthor(), book.getGenre());
    }

    @Override
    public void updateNameById(Long id, String newLabel) {
        Map<String, Object> params = Map.of("id", id,
                                            "newLabel", newLabel);
        namedParameterJdbcTemplate.update("UPDATE book SET label = :newLabel WHERE id = :id", params);
    }

    @Override
    public void deleteById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcTemplate.update("DELETE FROM book WHERE id = :id", params);
    }

    @Override
    public List<Book> getAll() throws CollectionEmptyException {
        List<Book> books;
        try {
            books = namedParameterJdbcTemplate.query(
                    "SELECT book.id, book.label, book.author_id, author.first_name, author.last_name, " +
                            "book.genre_id, genre.name " +
                         "FROM book " +
                         "JOIN author ON author.id = book.author_id " +
                         "JOIN genre ON genre.id = book.genre_id",
                    new BookMapper());
        } catch (DataAccessException exception) {
            throw new CollectionEmptyException(exception.getMessage(), exception.getCause());
        }
        return books;
    }

    @Override
    public Optional<Book> getById(Long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        String query = "SELECT book.id, book.label, book.author_id, author.first_name, author.last_name, " +
                "book.genre_id, genre.name " +
                "FROM book " +
                "JOIN author ON author.id = book.author_id " +
                "JOIN genre ON genre.id = book.genre_id " +
                "WHERE book.id = :id";
        return selectQueryForObject(query, params);
    }

    @Override
    public Optional<Book> getByLabelAndAuthor(String bookLabel, String authorFirstName, String authorLastName) {
        Map<String, Object> params = Map.of("book_label", bookLabel,
                "author_first_name", authorFirstName,
                "author_last_name", authorLastName);
        String query = "SELECT book.id, book.label, book.author_id, author.first_name, author.last_name, " +
                    "book.genre_id, genre.name " +
                "FROM book " +
                "JOIN author ON author.id = book.author_id " +
                "JOIN genre ON genre.id = book.genre_id " +
                "WHERE book.label = :book_label AND author.first_name = :author_first_name " +
                "AND author.last_name = :author_last_name";

        return selectQueryForObject(query, params);
    }

    private Optional<Book> selectQueryForObject(String query, Map<String, Object> params) {
        Optional<Book> optionalBook;
        try {
            optionalBook = Optional.ofNullable(
                    namedParameterJdbcTemplate.queryForObject(query, params, new BookMapper()));
        } catch (DataAccessException exception) {
            return Optional.empty();
        }
        return optionalBook;
    }

    private static class BookMapper implements RowMapper<Book> {

        @Override
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            long id = resultSet.getLong("id");
            long authorId = resultSet.getLong("author_id");
            long genreId = resultSet.getLong("genre_id");
            String label = resultSet.getString("label");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String genreName = resultSet.getString("name");
            return new Book(id, label, new Author(authorId, firstName, lastName), new Genre(genreId, genreName));
        }
    }

}