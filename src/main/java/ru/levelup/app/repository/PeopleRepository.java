package ru.levelup.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.levelup.app.model.Person;


@Repository
public interface PeopleRepository extends JpaRepository<Person, Long> {
}
