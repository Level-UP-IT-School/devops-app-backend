package ru.levelup.devops_app.exceptions;


public class PersonNotSuccessEditedException extends RuntimeException {
    public PersonNotSuccessEditedException(String message) {
        super(message);
    }
}
