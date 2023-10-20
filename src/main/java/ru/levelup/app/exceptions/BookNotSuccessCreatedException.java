package ru.levelup.app.exceptions;

public class BookNotSuccessCreatedException extends RuntimeException {
    public BookNotSuccessCreatedException(String message) {
        super(message);
    }
}
