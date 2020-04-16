package ru.otus.hw7SpringData.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw7SpringData.model.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@Import(BookRepository.class)
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void insert() {
        String name = "test";
        Long id = bookRepository.insert(new Book(null, name));

        Book expectedBook = new Book(id, name);
        checkSelect(expectedBook);
    }

    @Test
    void update() {
        Book update = new Book(
                bookRepository.insert(new Book(null, "test")),
                "test2"
        );
        bookRepository.update(update);
        checkSelect(update);
    }

    @Test
    void delete() {
        Long id = bookRepository.insert(new Book(null, "test"));

        bookRepository.delete(id);
        assertNull(bookRepository.select(id));
    }

    @Test
    void selectAll() {
        Long id3 = bookRepository.insert(new Book(null,"test3"));
        Long id4 = bookRepository.insert(new Book(null,"test4"));
        Long id5 = bookRepository.insert(new Book(null,"test5"));

        Book expected3 = new Book(id3, "test3");
        Book expected4 = new Book(id4, "test4");
        Book expected5 = new Book(id5, "test5");

        List<Book> books = bookRepository.selectAll();

        assertTrue(books.contains(expected3));
        assertTrue(books.contains(expected4));
        assertTrue(books.contains(expected5));
    }

    private void checkSelect(Book expectedBook) {
        assertEquals(
                expectedBook,
                bookRepository.select(expectedBook.getId())
        );
    }
}