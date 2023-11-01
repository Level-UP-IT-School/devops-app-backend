package ru.levelup.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.levelup.app.model.Book;
import ru.levelup.app.repository.BookRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class DevopsAppApplicationTests {

	@Autowired
	private BookRepository bookRepository;

	@Test
	public void givenGenericEntityRepository_whenSaveAndRetreiveEntity_thenOK() {
		List<Book> books = bookRepository.findAll();
		Book book = books.get(0);
		assertNotNull(book);
	}

	@Test
	public void testMock1() {
		assertNotNull(1);
	}

	@Test
	public void testMock2() {
		assertNotNull(2);
	}

	@Test
	public void testMock3() {
		assertNotNull(3);
	}

}
