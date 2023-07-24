package com.tokmakov.hw09.dao.jdbc_impl;

import com.tokmakov.hw09.dao.AuthorDao;
import com.tokmakov.hw09.domain.Author;
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
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorDaoJdbc implements AuthorDao {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Optional<Author> getByFirstAndLastNames(String firstName, String lastName) {
        Map<String, Object> params = Map.of("firstName", firstName,
                                            "lastName", lastName);
        Optional<Author> authorOptional;
        try {
            authorOptional = Optional.ofNullable(
                    namedParameterJdbcTemplate.queryForObject(
                            "SELECT id, first_name, last_name " +
                                "FROM author " +
                                "WHERE first_name = :firstName AND last_name = :lastName",
                            params, new AuthorMapper()));
        } catch (DataAccessException exception) {
            return Optional.empty();
        }
        return authorOptional;
    }

    @Override
    public Author insert(Author author) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("firstName", author.getFirstName())
                .addValue("lastName", author.getLastName());
        namedParameterJdbcTemplate.update("INSERT INTO author (first_name, last_name) " +
                        "VALUES (:firstName, :lastName)", params, keyHolder);
        return new Author(keyHolder.getKey().longValue(), author.getFirstName(), author.getLastName());
    }

    private static class AuthorMapper implements RowMapper<Author> {
        @Override
        public Author mapRow(ResultSet rs, int rowNum) throws SQLException {
            Long id = rs.getLong("id");
            String firstName = rs.getString("first_name");
            String lastName = rs.getString("last_name");
            return new Author(id, firstName, lastName);
        }
    }
}
