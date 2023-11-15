package ru.levelup.app.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.levelup.app.dto.PersonDTO;
import ru.levelup.app.exceptions.PersonErrorResponse;
import ru.levelup.app.exceptions.PersonNotSuccessCreatedException;
import ru.levelup.app.exceptions.PersonNotSuccessEditedException;
import ru.levelup.app.model.Book;
import ru.levelup.app.model.Person;
import ru.levelup.app.service.PeopleService;


import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/v1/people")
public class PeopleController {

    private final PeopleService peopleService;

    @Autowired
    public PeopleController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }


    @GetMapping()
    public List<PersonDTO> getPeople() {
        return peopleService.findAll();
    }

    @GetMapping("/{id}")
    public PersonDTO getPersonById(@PathVariable Long id) {
        return peopleService.convertToPersonDTO(peopleService.findById(id));
    }


    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO, BindingResult bindingResult) {

//        if (bindingResult.hasErrors()) {
//            StringBuilder builder = new StringBuilder();
//
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//            fieldErrors.forEach(x -> builder.append(x.getField()).append(" - ").append(x.getDefaultMessage()));
//            throw new PersonNotSuccessCreatedException(builder.toString());
//        }
        peopleService.save(convertToPerson(personDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public List<Book> editPerson(@PathVariable("id") Long id , @RequestBody @Valid PersonDTO personDTO,
                                 BindingResult bindingResult) {

//        if (bindingResult.hasErrors()) {
//            StringBuilder builder = new StringBuilder();
//
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//            fieldErrors.forEach(x -> builder.append(x.getField()).append(" - ").append(x.getDefaultMessage()));
//            throw new PersonNotSuccessEditedException(builder.toString());
//        }
        return peopleService.update(id, personDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePerson(@PathVariable("id") Long id) {
        peopleService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    public Person convertToPerson(PersonDTO personDTO) {
        Person p = new Person();
        p.setId((long) (peopleService.findAllPersons().size() + 1));
        p.setAge(personDTO.getAge());
        p.setPersonName(personDTO.getName());
        p.setPhoneNumber(personDTO.getPhoneNumber());
        return p;
    }

    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handleException(Exception ex) {
        PersonErrorResponse errorResponse = new PersonErrorResponse(ex.getLocalizedMessage(), new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
