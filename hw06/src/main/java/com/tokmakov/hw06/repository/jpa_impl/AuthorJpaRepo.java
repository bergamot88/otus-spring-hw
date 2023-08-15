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
        Optional<Author> authorOptional;
        TypedQuery<Author> query = entityManager.createQuery(
                "SELECT book FROM Book book WHERE first_name = :firstName AND last_name = :lastName", Author.class);
        query.setParameter("firstName", firstName);
        query.setParameter("lastName", lastName);
        try {
            authorOptional = Optional.of(query.getSingleResult());
        } catch (NoResultException e) {
            return Optional.empty();
        }
        return authorOptional;
    }

    @Override
    public Author insert(Author author) {
        entityManager.persist(author);
        return null;
    }
}
