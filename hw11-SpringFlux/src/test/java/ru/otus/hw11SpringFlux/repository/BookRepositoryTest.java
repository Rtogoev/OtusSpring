package ru.otus.hw11SpringFlux.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.hw11SpringFlux.model.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void save() {
        String name = "test";
        Book expectedBook = bookRepository.save(createBook(name));
        checkSelect(expectedBook);
    }

    private Book createBook(String name) {
        return new Book(
                name,
                null,
                null,
                null,
                null
        );
    }

    @Test
    void update() {
        Book update = createBook(
                bookRepository.save(createBook("save")).getId(),
                "test2"
        );
        bookRepository.save(createBook(update.getId(), "test2"));
        checkSelect(update);
    }

    private Book createBook(String id, String name) {
        return new Book(
                name,
                id,
                null,
                null,
                null
        );
    }

    @Test
    void delete() {
        String id = bookRepository.save(createBook("test")).getId();
        bookRepository.deleteById(id);
        assertNull(bookRepository.findById(id).orElse(null));
    }

    @Test
    void selectAll() {
        Book expected3 = bookRepository.save(createBook( "test3"));
        Book expected4 = bookRepository.save(createBook( "test4"));
        Book expected5 = bookRepository.save(createBook( "test5"));

        List<Book> books = bookRepository.findAll();

        assertTrue(books.contains(expected3));
        assertTrue(books.contains(expected4));
        assertTrue(books.contains(expected5));
    }

    private void checkSelect(Book expectedBook) {
        assertEquals(
                expectedBook,
                bookRepository.findById(expectedBook.getId()).orElse(null)
        );
    }
}