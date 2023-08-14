package com.tokmakov.hw05.service.impl;

import com.tokmakov.hw05.dao.AuthorDao;
import com.tokmakov.hw05.domain.Author;
import com.tokmakov.hw05.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = AuthorServiceImpl.class)
public class AuthorServiceImplTest {

    @MockBean
    private AuthorDao authorDao;

    @Autowired
    private AuthorService authorService;

    private static final Long EXIST_AUTHOR_ID = 1L;
    private static final String EXIST_AUTHOR_FIRST_NAME = "Alexander";
    private static final String EXIST_AUTHOR_LAST_NAME = "Zinoviev";

    private static final String NEW_AUTHOR_FIRST_NAME = "Eduard1";
    private static final String NEW_AUTHOR_LAST_NAME = "Limonov1";

    @Test
    void testFindFirst() {
        Author expectedAuthor =
                new Author(EXIST_AUTHOR_ID, EXIST_AUTHOR_FIRST_NAME, EXIST_AUTHOR_LAST_NAME);

        when(authorDao.getByFirstAndLastNames(expectedAuthor.getFirstName(), expectedAuthor.getLastName()))
                .thenReturn(Optional.of(expectedAuthor));

        Optional<Author> author = authorService.findFirst(expectedAuthor);

        assertThat(author, not(Optional.empty()));
        assertThat(author.get(), equalTo(expectedAuthor));
    }

    @Test
    void testAdd() {
        Author newAuthor = new Author(NEW_AUTHOR_FIRST_NAME, NEW_AUTHOR_LAST_NAME);

        when(authorDao.insert(newAuthor)).thenReturn(newAuthor);

        newAuthor = authorService.add(newAuthor);

        when(authorDao.getByFirstAndLastNames(newAuthor.getFirstName(), newAuthor.getLastName()))
                .thenReturn(Optional.of(newAuthor));

        Optional<Author> authorOptional = authorService.findFirst(newAuthor);

        assertThat(authorOptional, not(Optional.empty()));
        assertThat(authorOptional.get(), equalTo(newAuthor));
    }
}
