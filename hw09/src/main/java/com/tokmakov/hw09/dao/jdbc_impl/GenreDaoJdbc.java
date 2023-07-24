package com.tokmakov.hw09.dao.jdbc_impl;

import com.tokmakov.hw09.dao.GenreDao;
import com.tokmakov.hw09.domain.Genre;
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
import java.util.Map;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Genre insert(Genre genre) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource params = new MapSqlParameterSource().addValue("name", genre.getName());
        namedParameterJdbcTemplate.update("INSERT INTO genre (name) VALUES (:name)", params, keyHolder);
        return new Genre(keyHolder.getKey().longValue(), genre.getName());
    }

    @Override
    public Optional<Genre> getByName(String name) {
        Map<String, Object> params = Collections.singletonMap("name", name);
        Optional<Genre> genreOptional;
        try {
            genreOptional = Optional.ofNullable(
                    namedParameterJdbcTemplate.queryForObject(
                            "SELECT id, name FROM genre WHERE name = :name",
                            params, new GenreMapper()));
        } catch (DataAccessException exception) {
            return Optional.empty();
        }
        return genreOptional;
    }

    private static class GenreMapper implements RowMapper<Genre> {
        @Override
        public Genre mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            return new Genre(id, name);
        }
    }
}
