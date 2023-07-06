package com.tokmakov.hw09.domain;

import lombok.Data;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

@Data
public class Genre {

    private Long id;
    private String name;

    public Genre(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Genre(String name) {
        this.name = name;
    }

    public MapSqlParameterSource getAsArgs() {
        return new MapSqlParameterSource()
                .addValue("id", this.id)
                .addValue("name", this.name);
    }
}
