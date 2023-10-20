package ru.levelup.app.service;

import org.springframework.stereotype.Service;
import ru.levelup.app.dto.BookDTO;
import ru.levelup.app.dto.PersonDTO;
import ru.levelup.app.model.Book;
import ru.levelup.app.model.Person;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private List<Book> books = new ArrayList<>();

    public List<Book> findAll() {
        return books;
    }

    public Book findById(Long id) {
        return books
                .stream().filter(x -> x.getId().equals(id))
                .findFirst().orElse(null);
    }

    public void save(Book book) {
        books.add(book);
    }

    public void update(Long id, BookDTO bookDTO) {
        Book byId = findById(id);
        books.remove(byId);
        byId.setName(bookDTO.getName());
        byId.setAuthor(bookDTO.getAuthor());
        byId.setGenre(bookDTO.getGenre());
        byId.setDescription(bookDTO.getDescription());
        books.add(byId);
    }

    public void delete(Long id) {
        Book byId = findById(id);
        books.remove(byId);
    }

    public Book findByItemName(String name) {
        return null;
    }

    public Book findByOwner(PersonDTO owner) {
        return null;
    }

}
