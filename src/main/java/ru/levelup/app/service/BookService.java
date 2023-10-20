package ru.levelup.devops_app.service;

import org.springframework.stereotype.Service;
import ru.levelup.devops_app.dto.BookDTO;
import ru.levelup.devops_app.dto.PersonDTO;

import java.util.List;

@Service
public class BookService {

    public List<BookDTO> findAll() {
        return null;
    }

    public BookDTO findById(Long id) {
        return null;
    }

    public void save(BookDTO item) {

    }

    public void update(Long id, BookDTO item) {
    }

    public void delete(Long id) {
    }

    public BookDTO findByItemName(String name) {
        return null;
    }

    public BookDTO findByOwner(PersonDTO owner) {
        return null;
    }

}
