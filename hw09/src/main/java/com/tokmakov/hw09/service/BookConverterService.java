package com.tokmakov.hw09.service;

import com.tokmakov.hw09.domain.Book;

import java.util.List;

public interface BookConverterService {
    String convertToString(Book book);

    String convertToString(List<Book> books);
}
