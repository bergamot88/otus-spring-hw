package com.tokmakov.hw06.domain;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "book")
@NamedEntityGraph(name = "book-author-genre-graph", attributeNodes = {
        @NamedAttributeNode("author"),
        @NamedAttributeNode("genre")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "label")
    private String label;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
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
