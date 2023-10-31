package ru.levelup.app.model;

import lombok.Data;

@Data
public class Book {

    private Long id;

    private String bookName;

    private String author;

    private String genre;

    private String description;

    private Long personId;
}
