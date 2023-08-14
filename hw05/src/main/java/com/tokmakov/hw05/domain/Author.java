package com.tokmakov.hw05.domain;

import lombok.Data;

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
}
