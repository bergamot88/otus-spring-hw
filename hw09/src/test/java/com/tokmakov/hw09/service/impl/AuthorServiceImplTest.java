package com.tokmakov.hw09.service.impl;

import com.tokmakov.hw09.dao.AuthorDao;
import com.tokmakov.hw09.domain.Author;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;

@SpringBootTest
public class AuthorServiceImplTest {

    @Mock
    private AuthorDao authorDao;

    @InjectMocks
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
