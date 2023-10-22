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
import ru.levelup.app.service.BookService;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/v1/books")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
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

        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(x -> builder.append(x.getField()).append(" - ").append(x.getDefaultMessage()));
            throw new BookNotSuccessCreatedException(builder.toString());
        }
        bookService.save(convertToBook(bookDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<HttpStatus> editBook(@PathVariable("id") Long id, @RequestBody @Valid BookDTO bookDTO,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(x -> builder.append(x.getField()).append(" - ").append(x.getDefaultMessage()));
            throw new BookNotSuccessEditedException(builder.toString());
        }
        bookService.update(id, bookDTO);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable("id") Long id) {
        bookService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


    private Book convertToBook(BookDTO bookDTO) {
        Book p = new Book();
        p.setBookName(bookDTO.getName());
        p.setAuthor(bookDTO.getAuthor());
        p.setGenre(bookDTO.getGenre());
        p.setDescription(bookDTO.getDescription());
        return p;
    }

    @ExceptionHandler
    private ResponseEntity<BookErrorResponse> handleException(Exception ex) {
        BookErrorResponse errorResponse = new BookErrorResponse(ex.getLocalizedMessage(), new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
