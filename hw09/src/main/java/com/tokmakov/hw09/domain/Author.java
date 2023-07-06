package com.tokmakov.hw09.domain;

import lombok.Data;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

@Data
public class Author {

    private Long id;
    private String firstName;
    private String lastName;

    public Author(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public MapSqlParameterSource getAsArgs() {
        return new MapSqlParameterSource()
                .addValue("id", this.id)
                .addValue("firstName", this.firstName)
                .addValue("lastName", this.lastName);
    }
}
