package com.tokmakov.hw09.domain;

import lombok.Data;


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

    public Book(String label, Author author) {
        this.label = label;
        this.author = author;
    }
}
