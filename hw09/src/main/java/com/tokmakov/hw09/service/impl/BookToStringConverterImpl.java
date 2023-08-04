package com.tokmakov.hw09.service.impl;

import com.tokmakov.hw09.domain.Book;
import com.tokmakov.hw09.service.BookConverterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookToStringConverterImpl implements BookConverterService<String> {

    @Override
    public String convert(Book book) {
        return String.format("'%s' - %s %s", book.getLabel(), book.getAuthor().getFirstName(),
                book.getAuthor().getLastName());
    }

    @Override
    public String convert(List<Book> books) {
        StringBuilder stringBuilder = new StringBuilder();
        books.forEach(book -> stringBuilder.append(this.convert(book))
                                           .append(System.lineSeparator()));
        return stringBuilder.toString();
    }
}
