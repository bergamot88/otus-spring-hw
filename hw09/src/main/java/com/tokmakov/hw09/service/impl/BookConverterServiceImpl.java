package com.tokmakov.hw09.service.impl;

import com.tokmakov.hw09.domain.Book;
import com.tokmakov.hw09.service.BookConverterService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookConverterServiceImpl implements BookConverterService {

    public String convertToString(Book book) {
        return String.format("'%s' - %s %s", book.getLabel(), book.getAuthor().getFirstName(),
                book.getAuthor().getLastName());
    }

    public String convertToString(List<Book> books) {
        StringBuilder stringBuilder = new StringBuilder();
        books.forEach(book -> stringBuilder.append(convertToString(book))
                                           .append(System.lineSeparator()));
        return stringBuilder.toString();
    }
}
