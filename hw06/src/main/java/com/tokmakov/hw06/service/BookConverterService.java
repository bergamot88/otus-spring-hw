package com.tokmakov.hw06.service;

import com.tokmakov.hw06.domain.Book;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

public interface BookConverterService<T> extends Converter<Book, T> {
    T convert(Book book);

    T convert(List<Book> books);
}
