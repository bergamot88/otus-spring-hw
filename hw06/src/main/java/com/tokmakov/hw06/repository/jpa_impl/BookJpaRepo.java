package com.tokmakov.hw06.repository.jpa_impl;

import com.tokmakov.hw06.domain.Book;
import com.tokmakov.hw06.exception.CollectionEmptyException;
import com.tokmakov.hw06.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.repository.EntityGraph.EntityGraphType.FETCH;

@Repository
@RequiredArgsConstructor
public class BookJpaRepo implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Book insert(Book book) {
        entityManager.persist(book);
        return book;
    }

    @Override
    public Book update(Book book) {
        return entityManager.merge(book);
    }

    @Override
    public void remove(Book book) {
        entityManager.remove(book);
    }

    @Override
    public List<Book> getAll() throws CollectionEmptyException {
        EntityGraph<?> graph = entityManager.getEntityGraph("books_genres_graph");
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM book b", Book.class);
        query.setHint("jakarta.persistence.fetchgraph", graph);
        return query.getResultList();
    }

    @Override
    public Optional<Book> getById(Long id) {
        return Optional.ofNullable(entityManager.find(Book.class, id));
    }

    @Override
    public Optional<Book> getByLabelAndAuthor(String label, String authorFirstName, String authorLastName) {
        return Optional.empty();
    }
}
