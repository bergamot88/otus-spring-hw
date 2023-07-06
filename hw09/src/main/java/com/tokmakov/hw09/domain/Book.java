package com.tokmakov.hw09.domain;

import lombok.Data;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;


@Data
public class Book {

    private Long id;

    private String label;

    private Author author;

    private Genre genre;

    public Book(Long id, String label, Author author, Genre genre) {
        this.id = id;
        this.label = label;
        this.author = author;
        this.genre = genre;
    }

    public Book(String label, Author author, Genre genre) {
        this.label = label;
        this.author = author;
        this.genre = genre;
    }

    public MapSqlParameterSource getAsArgs() {
        return new MapSqlParameterSource()
                .addValue("id", this.id)
                .addValue("label", this.label)
                .addValue("author_id", author.getId())
                .addValue("genre_id", genre.getId());
    }

    public String toString() {
        return String.format("'%s' - %s %s",
                this.label,
                this.author.getFirstName(),
                this.author.getLastName());
    }
}
