package com.tokmakov.hw06.repository.jpa_impl;

import com.tokmakov.hw06.domain.Author;
import com.tokmakov.hw06.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class AuthorJpaRepo implements AuthorRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Author> getByFirstAndLastNames(String firstName, String lastName) {
        TypedQuery<Author> query = entityManager.createQuery(
                "SELECT a FROM Author a WHERE a.firstName = :first_name AND a.lastName = :last_name", Author.class);
        query.setParameter("first_name", firstName);
        query.setParameter("last_name", lastName);
        return Optional.ofNullable(query.getSingleResult());
    }

    @Override
    public Author insert(Author author) {
        entityManager.persist(author);
        return author;
    }
}
