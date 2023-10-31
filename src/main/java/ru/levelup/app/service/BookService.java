package ru.levelup.app.service;

import org.springframework.stereotype.Service;
import ru.levelup.app.dto.BookDTO;
import ru.levelup.app.model.Book;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final List<Book> books = new ArrayList<>();
    private Long COUNTER = 0L;

    public List<Book> findAll() {
        return books;
    }

    public Book findById(Long id) {
        return books
                .stream().filter(x -> x.getId().equals(id))
                .findFirst().orElse(null);
    }

    public void save(Book book) {
        book.setId(++COUNTER);
        books.add(book);
    }

    public void update(Long id, BookDTO bookDTO) {
        Book byId = findById(id);
        books.remove(byId);
        byId.setBookName(bookDTO.getName());
        byId.setAuthor(bookDTO.getAuthor());
        byId.setGenre(bookDTO.getGenre());
        byId.setDescription(bookDTO.getDescription());
        books.add(byId);
    }

    public void delete(Long id) {
        Book byId = findById(id);

        books.remove(byId);
    }



}
