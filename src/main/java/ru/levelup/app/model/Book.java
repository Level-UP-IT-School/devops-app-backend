package ru.levelup.app.model;

import lombok.Data;

@Data
public class Book {

    private Long id;

    private String name;

    private String author;

    private String genre;

    private String description;

    private Long person;
}
