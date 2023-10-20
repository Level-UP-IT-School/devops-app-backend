package ru.levelup.app.exceptions;

public class BookNotSuccessEditedException extends RuntimeException {
    public BookNotSuccessEditedException(String message) {
        super(message);
    }
}
