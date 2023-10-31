package ru.levelup.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.app.dto.BookDTO;
import ru.levelup.app.dto.PersonDTO;
import ru.levelup.app.model.Book;
import ru.levelup.app.model.Person;
import ru.levelup.app.repository.BookRepository;

import java.util.ArrayList;
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

    public List<BookDTO> findAllBooksDTO() {
        List<Book> all = bookRepository.findAll();
        List<BookDTO> result = new ArrayList<>();
        all.forEach(x -> {
            BookDTO bookDTO = new BookDTO();
            bookDTO.setName(x.getBookName());
            bookDTO.setAuthor(x.getAuthor());
            bookDTO.setDescription(x.getDescription());
            bookDTO.setPersonId(x.getPersonId());
            bookDTO.setGenre(x.getGenre());
            result.add(bookDTO);
        });
        return result;
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
            b.setPersonId(bookDTO.getPersonId());
            bookRepository.save(b);
        }
    }

    @Transactional
    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public PersonDTO convertToPersonDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(person.getPersonName());
        personDTO.setPhoneNumber(person.getPhoneNumber());
        List<Long> res = new ArrayList<>();
        personDTO.setBooksId(res);
        return personDTO;
    }

    public List<Book> findByPersonId(Long id) {
        return bookRepository.findByPersonId(id);
    }

}
