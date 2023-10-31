package ru.levelup.app.dto;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.levelup.app.model.Book;

import java.util.List;

@Data
public class PersonDTO {

    @NotEmpty(message = "Name should not to be empty")
    @Size(min = 2, max = 50, message = "Lenght of name should be min 2 symbols, max 50 symbols")
    private String name;

    @Min(value = 0, message = "Age should be min 0 year")
    private int age;

    @NotEmpty(message = "PhoneNumber should not to be empty")
    private String phoneNumber;

    private List<BookDTO> books;
    private List<Long> booksId;

}
