package ru.levelup.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.levelup.app.dto.PersonDTO;
import ru.levelup.app.model.Book;
import ru.levelup.app.model.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class PeopleService {

    private List<Person> people = new ArrayList<>();

    private BookService bookService;
    private Long COUNTER = 0L;

    @Autowired
    public PeopleService(BookService bookService) {
        this.bookService = bookService;
    }


    public List<Person> findAll() {
        return people;
    }

    public Person findById(Long id) {
        return people
                .stream().filter(x -> x.getId().equals(id))
                .findFirst().orElse(null);
    }

    public void save(Person person) {
        person.setId(++COUNTER);
        people.add(person);
    }

    public List<Book> update(Long id, PersonDTO personDTOFromForm) {
        Person byId = findById(id);
        List<Book> books = null;
        if (byId == null) {
            return null;
        }
        byId.setAge(personDTOFromForm.getAge());
        byId.setName(personDTOFromForm.getName());
        byId.setPhoneNumber(personDTOFromForm.getPhoneNumber());
        books = editBooks(personDTOFromForm.getBooksId(), byId.getId(), bookService.findAll());

        return books;
    }

    public void delete(Long id) {
        Person byId = findById(id);
        people.remove(byId);
    }

    public List<Book> editBooks(List<Long> bookIds, Long id, List<Book> allBooks) {
        AtomicReference<Long> bookIdToDelete = new AtomicReference<>(null);
        Person p = findById(id);
        List<Book> booksToDelete = new ArrayList<>();
        // удаляем
        p.getBooks().forEach(x -> {
            if (!bookIds.contains(x.getId())) {
                bookIdToDelete.set(x.getId());
            }
            if (bookIdToDelete.get() != null) {
                booksToDelete.add(bookService.findById(bookIdToDelete.get()));
                bookIdToDelete.set(null);
            }
        });
        if (!booksToDelete.isEmpty()) {
            booksToDelete.forEach(x -> p.getBooks().remove(x));
        }
        // добавляем конкретному и убираем у остальных
        bookIds.forEach(x -> {
            Book b = bookService.findById(x);
            if (!p.getBooks().contains(b)) {
                people.forEach(s -> {
                    if (s.getBooks().contains(b) && !s.equals(p)) {
                        s.getBooks().remove(b);
                    }
                });
                p.getBooks().add(b);
                b.setPerson(p.getId());
                Objects.requireNonNull(allBooks.stream().filter(book ->
                        book.getId().equals(b.getId())).findFirst().orElse(null)).setPerson(p.getId());
            }
            people.remove(p);
            people.add(p);
        });
        return p.getBooks();
    }


}
