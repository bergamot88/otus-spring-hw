package com.tokmakov.hw06.exception;

public class CollectionEmptyException extends RuntimeException {
    public CollectionEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
