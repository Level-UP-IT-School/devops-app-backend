package ru.levelup.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.levelup.app.model.Book;
import ru.levelup.app.model.Person;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByPerson(Person person);
}
