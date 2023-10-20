package ru.levelup.app.exceptions;


public class PersonNotSuccessEditedException extends RuntimeException {
    public PersonNotSuccessEditedException(String message) {
        super(message);
    }
}
