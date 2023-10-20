package ru.levelup.devops_app.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDateTime;

@Data
public class Person {

    private Long id;

    private String name;

    private int age;

    private String phoneNumber;


}
