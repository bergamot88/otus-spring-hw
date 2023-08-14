package com.tokmakov.hw05.exception;

public class CollectionEmptyException extends RuntimeException {
    public CollectionEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
