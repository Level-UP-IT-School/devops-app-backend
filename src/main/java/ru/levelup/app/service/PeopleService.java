package ru.levelup.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.app.dto.PersonDTO;
import ru.levelup.app.model.Book;
import ru.levelup.app.model.Person;
import ru.levelup.app.repository.PeopleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final BookService bookService;

    public PeopleService(PeopleRepository peopleRepository, BookService bookService) {
        this.peopleRepository = peopleRepository;
        this.bookService = bookService;
    }


    public List<PersonDTO> findAll() {
        List<Person> all = peopleRepository.findAll();
        List<PersonDTO> result = new ArrayList<>();
        all.forEach(x -> {
            result.add(convertToPersonDTO(x));
        });
        return result;
    }

    public List<Person> findAllPersons() {
        return peopleRepository.findAll();
    }

    public Person findById(Long id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public List<Book> update(Long id, PersonDTO personDTO) {
        Person person = peopleRepository.findById(id).orElse(null);
        if (person != null) {
            personDTO.getBooksId().forEach(x -> {
                Book b = bookService.findById(x);
                if (b != null && !person.getBooks().contains(b)) {
                    b.setPerson(person);
                    bookService.save(b);
                }
            });
            person.getBooks().forEach(x -> {
                if (!personDTO.getBooksId().contains(x.getId())) {
                    Book b = bookService.findById(x.getId());
                    if (b != null && !person.getBooks().contains(b)) {
                        b.setPerson(null);
                        bookService.save(b);
                    }
                }
            });
            person.setAge(personDTO.getAge());
            person.setPersonName(personDTO.getName());
            person.setPhoneNumber(personDTO.getPhoneNumber());
            peopleRepository.saveAndFlush(person);
        }
        List<Book> books = bookService.findByPerson(person);
        books.forEach(x -> x.setPerson(null));
        return person != null ? books : null;
    }

    @Transactional
    public void delete(Long id) {
        peopleRepository.deleteById(id);
    }

    public PersonDTO convertToPersonDTO(Person person) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName(person.getPersonName());
        personDTO.setPhoneNumber(person.getPhoneNumber());
        List<Long> res = new ArrayList<>();
        person.getBooks().forEach(x -> {
            res.add(x.getId());
        });
        personDTO.setBooksId(res);
        return personDTO;
    }


}
