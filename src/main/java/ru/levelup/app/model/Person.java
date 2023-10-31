package ru.levelup.app.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode
public class Person {

    private Long id;

    private String name;

    private int age;

    private String phoneNumber;

    private List<Book> booksId;
    private List<Book> books;

    public List<Book> getBooksId() {
        if (booksId == null) {
            this.booksId = new ArrayList<>();
        }
        return booksId;
    }


}
