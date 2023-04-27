package com.tokmakov.hm03.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class IOServiceImplTest {
    private static final String TEXT_TO_PRINT = "Hello print";
    private static final String TEXT_TO_READ = "Hello read";

    private ByteArrayOutputStream byteArrayOutputStream;
    private IOService ioService;

    @BeforeEach
    void setUp() {
        byteArrayOutputStream = new ByteArrayOutputStream();

        InputStream in = new ByteArrayInputStream(TEXT_TO_READ.getBytes());
        ioService = new IOServiceImpl(in, new PrintStream(byteArrayOutputStream));
    }

    @Test
    void shouldPrintStringFromOutLnMethod() {
        ioService.outln(TEXT_TO_PRINT);
        assertEquals(byteArrayOutputStream.toString(), TEXT_TO_PRINT + System.lineSeparator());
    }

    @Test
    void shouldPrintStringFromReadStringMethod() {
        assertEquals(ioService.readString(), TEXT_TO_READ);
    }
}
