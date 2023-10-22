package ru.levelup.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.levelup.app.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
