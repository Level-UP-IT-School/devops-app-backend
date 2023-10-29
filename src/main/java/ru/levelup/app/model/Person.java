package ru.levelup.app.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Person {

    private Long id;

    private String name;

    private int age;

    private String phoneNumber;

    private List<Book> books;

    public List<Book> getBooks() {
        if (books == null) {
            this.books = new ArrayList<>();
        }
        return books;
    }
}
