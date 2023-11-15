package ru.levelup.app.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.levelup.app.dto.BookDTO;
import ru.levelup.app.exceptions.BookErrorResponse;
import ru.levelup.app.exceptions.BookNotSuccessCreatedException;
import ru.levelup.app.exceptions.BookNotSuccessEditedException;
import ru.levelup.app.model.Book;
import ru.levelup.app.model.Person;
import ru.levelup.app.service.BookService;
import ru.levelup.app.service.PeopleService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;


@CrossOrigin
@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final BookService bookService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BookService bookService, PeopleService peopleService) {
        this.bookService = bookService;
        this.peopleService = peopleService;
    }


    @GetMapping()
    public List<Book> getBooks() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id) {
        return bookService.findById(id);
    }


    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult) {

//        if (bindingResult.hasErrors()) {
//            StringBuilder builder = new StringBuilder();
//
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//            fieldErrors.forEach(x -> builder.append(x.getField()).append(" - ").append(x.getDefaultMessage()));
//            throw new BookNotSuccessCreatedException(builder.toString());
//        }
        Book book = convertToBook(bookDTO);
//        if (bookDTO.getPersonId() != null && book.getPersonId() == null) {
//            throw new BookNotSuccessCreatedException("Владельца книги с таким id не существует");
//        }
        bookService.save(book);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> editBook(@PathVariable("id") Long id, @RequestBody @Valid BookDTO bookDTO,
                                               BindingResult bindingResult) {

//        if (bindingResult.hasErrors()) {
//            StringBuilder builder = new StringBuilder();
//
//            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
//            fieldErrors.forEach(x -> builder.append(x.getField()).append(" - ").append(x.getDefaultMessage()));
//            throw new BookNotSuccessEditedException(builder.toString());
//        }
        bookService.update(id, bookDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {
        deleteBookFromPerson(id);
        bookService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private Book convertToBook(BookDTO bookDTO) {
        Book b = new Book();
        b.setBookName(bookDTO.getName());
        b.setAuthor(bookDTO.getAuthor());
        b.setGenre(bookDTO.getGenre());
        b.setDescription(bookDTO.getDescription());
        b.setPersonId(bookDTO.getPersonId());
        return b;
    }

    @ExceptionHandler
    private ResponseEntity<BookErrorResponse> handleException(Exception ex) {
        BookErrorResponse errorResponse = new BookErrorResponse(ex.getLocalizedMessage(), new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private void deleteBookFromPerson(Long id) {
        List<Person> people = peopleService.findAll();
        List<Person> personToDeleteBook = new ArrayList<>();
        AtomicReference<Book> book = new AtomicReference<>();
        people.forEach(x -> x.getBooksId().forEach(b -> {
            if(id.equals(b.getId())) {
                personToDeleteBook.add(x);
                Objects.requireNonNull(book).set(b);
            }
        }));
        personToDeleteBook.get(0).getBooksId().remove(book.get());
        people.remove(personToDeleteBook.get(0));
        people.add(personToDeleteBook.get(0));
    }
}
