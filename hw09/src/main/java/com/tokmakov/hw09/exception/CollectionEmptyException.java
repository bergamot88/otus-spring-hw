package com.tokmakov.hw09.exception;

public class CollectionEmptyException extends RuntimeException {
    public CollectionEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
