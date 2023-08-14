package com.tokmakov.hw05.dao.jdbc_impl;

import com.tokmakov.hw05.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@JdbcTest
@Import(AuthorDaoJdbc.class)
public class AuthorDaoJdbcTest {

    @Autowired
    private AuthorDaoJdbc authorDaoJdbc;

    private static final Long EXIST_AUTHOR_ID = 1L;
    private static final String EXIST_AUTHOR_FIRST_NAME = "Alexander";
    private static final String EXIST_AUTHOR_LAST_NAME = "Zinoviev";

    private static final String NEW_AUTHOR_FIRST_NAME = "Eduard";
    private static final String NEW_AUTHOR_LAST_NAME = "Limonov";

    @Test
    void testGetByFirstAndLastNames() {
        Author expectedAuthor =
                new Author(EXIST_AUTHOR_ID, EXIST_AUTHOR_FIRST_NAME, EXIST_AUTHOR_LAST_NAME);
        Optional<Author> authorFromDB = authorDaoJdbc
                .getByFirstAndLastNames(expectedAuthor.getFirstName(), expectedAuthor.getLastName());
        assertThat(authorFromDB, not(Optional.empty()));
        assertThat(authorFromDB.get(), equalTo(expectedAuthor));
    }

    @Test
    void testInsert() {
        Author author = new Author(NEW_AUTHOR_FIRST_NAME, NEW_AUTHOR_LAST_NAME);
        author = authorDaoJdbc.insert(author);

        Author authorFromDB = authorDaoJdbc
                .getByFirstAndLastNames(author.getFirstName(), author.getLastName())
                .orElseThrow();

        assertThat(authorFromDB, notNullValue());
        assertThat(authorFromDB, equalTo(author));
    }
}
