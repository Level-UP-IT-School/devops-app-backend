package ru.levelup.devops_app.service;

import org.springframework.stereotype.Service;
import ru.levelup.devops_app.dto.PersonDTO;
import ru.levelup.devops_app.model.Person;


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
        byId.setAge(personDTOFromForm.getAge());
    }

    public void delete(Long id) {
    }


}
