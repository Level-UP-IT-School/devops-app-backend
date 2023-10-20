package ru.levelup.app.exceptions;

public class PersonNotSuccessCreatedException extends RuntimeException {
    public PersonNotSuccessCreatedException(String message) {
        super(message);
    }
}
