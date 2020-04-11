package ru.otus.hw5JdbcShell.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw5JdbcShell.model.dto.Book;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
@JdbcTest
@Import(BookRepository.class)
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void insert() {
        String name = "test";
        Long id = bookRepository.insert(name);

        Book expectedBook = new Book(id, name);
        checkSelect(expectedBook);
    }

    @Test
    void update() {
        Long id = bookRepository.insert("test");
        bookRepository.update(id, "test2");
        checkSelect(new Book(id, "test2"));
    }

    @Test
    void delete() {
        Long id = bookRepository.insert("test");

        bookRepository.delete(id);
        assertNull(bookRepository.select(id));
    }

    @Test
    void selectAll() {
        Long id3 = bookRepository.insert("test3");
        Long id4 = bookRepository.insert("test4");
        Long id5 = bookRepository.insert("test5");

        Book expected3 = new Book(id3, "test3");
        Book expected4 = new Book(id4, "test4");
        Book expected5 = new Book(id5, "test5");

        Set<Book> books = bookRepository.selectAll();

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