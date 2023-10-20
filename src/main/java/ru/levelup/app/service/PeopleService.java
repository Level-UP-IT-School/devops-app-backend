package ru.levelup.app.service;

import org.springframework.stereotype.Service;
import ru.levelup.app.dto.PersonDTO;
import ru.levelup.app.model.Person;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleService {

    private List<Person> people = new ArrayList<>();


    public List<Person> findAll() {
        return people;
    }

    public Person findById(Long id) {
        return people
                .stream().filter(x -> x.getId().equals(id))
                .findFirst().orElse(null);
    }

    public void save(Person person) {
        people.add(person);
    }

    public void update(Long id, PersonDTO personDTOFromForm) {
        Person byId = findById(id);
        people.remove(byId);
        byId.setAge(personDTOFromForm.getAge());
        byId.setName(personDTOFromForm.getName());
        byId.setPhoneNumber(personDTOFromForm.getPhoneNumber());
        people.add(byId);
    }

    public void delete(Long id) {
        Person byId = findById(id);
        people.remove(byId);
    }


}
