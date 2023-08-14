package com.tokmakov.hw05.service;

import com.tokmakov.hw05.domain.Book;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public interface BookConverterService<T> extends Converter<Book, T> {
    T convert(Book book);

    T convert(List<Book> books);
}
