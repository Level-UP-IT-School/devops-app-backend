package ru.levelup.app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class BookDTO {

    @NotEmpty(message = "BookName should not to be empty")
    @Size(min = 2, max = 50, message = "Lenght of bookName should be min 2 symbols, max 50 symbols")
    private String name;

    @NotEmpty(message = "Author should not to be empty")
    @Size(min = 2, max = 50, message = "Lenght of Author should be min 2 symbols, max 50 symbols")
    private String author;

    @NotEmpty(message = "Genre should not to be empty")
    @Size(min = 2, max = 50, message = "Lenght of genre should be min 2 symbols, max 50 symbols")
    private String genre;

    @NotEmpty(message = "Description should not to be empty")
    @Size(min = 2, max = 10000, message = "Lenght of description should be min 2 symbols, max 100 symbols")
    private String description;

    private Long person;

}
