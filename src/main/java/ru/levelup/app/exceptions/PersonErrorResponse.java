package ru.levelup.devops_app.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class PersonErrorResponse {
    private String msg;
    private Date dateOfError;
}
