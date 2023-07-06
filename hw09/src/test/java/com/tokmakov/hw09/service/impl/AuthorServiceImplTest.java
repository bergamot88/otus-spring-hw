package com.tokmakov.hw09.service.impl;

import com.tokmakov.hw09.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;

@SpringBootTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)
public class AuthorServiceImplTest {

    @Autowired
    private AuthorServiceImpl authorService;

    private static final Long EXIST_AUTHOR_ID = 1L;
    private static final String EXIST_AUTHOR_FIRST_NAME = "Alexander";
    private static final String EXIST_AUTHOR_LAST_NAME = "Zinoviev";

    private static final String NEW_AUTHOR_FIRST_NAME = "Eduard";
    private static final String NEW_AUTHOR_LAST_NAME = "Limonov";

    @Test
    void testFindFirst() {
        Author expectedAuthor =
                new Author(EXIST_AUTHOR_ID, EXIST_AUTHOR_FIRST_NAME, EXIST_AUTHOR_LAST_NAME);
        Optional<Author> author = authorService.findFirst(expectedAuthor);
        assertThat(author, not(Optional.empty()));
        assertThat(author.get(), equalTo(expectedAuthor));
    }

    @Test
    void testAdd() {
        Author author = new Author(NEW_AUTHOR_FIRST_NAME, NEW_AUTHOR_LAST_NAME);
        author = authorService.add(author);
        Optional<Author> authorOptional = authorService.findFirst(author);
        assertThat(authorOptional, not(Optional.empty()));
        assertThat(authorOptional.get(), equalTo(author));
    }
}
