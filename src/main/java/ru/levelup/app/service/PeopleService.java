package ru.levelup.app.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.levelup.app.dto.PersonDTO;
import ru.levelup.app.model.Person;
import ru.levelup.app.repository.PeopleRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;

    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }


    public List<Person> findAll() {
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
    public void update(Long id, PersonDTO person) {
        Person p = peopleRepository.findById(id).orElse(null);
        if (p != null) {
            p.setAge(person.getAge());
            p.setPersonName(person.getName());
            p.setPhoneNumber(person.getPhoneNumber());
            p.setBooks(person.getBooks());
            peopleRepository.save(p);
        }
    }

    @Transactional
    public void delete(Long id) {
        peopleRepository.deleteById(id);
    }


}
