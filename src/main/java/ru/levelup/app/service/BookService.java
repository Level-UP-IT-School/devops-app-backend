package ru.levelup.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.app.dto.BookDTO;
import ru.levelup.app.model.Book;
import ru.levelup.app.repository.BookRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Transactional
    public void update(Long id, BookDTO bookDTO) {
        Book b = bookRepository.findById(id).orElse(null);
        if (b != null) {
            b.setBookName(bookDTO.getName());
            b.setAuthor(bookDTO.getAuthor());
            b.setGenre(bookDTO.getGenre());
            b.setDescription(bookDTO.getDescription());
            b.setPerson(bookDTO.getOwner());
            bookRepository.save(b);
        }
    }

    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

}
