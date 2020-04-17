package ru.otus.hw7SpringData.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.hw7SpringData.model.Book;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BookRepositoryTest {
    @Autowired
    private BookRepository bookRepository;

    @Test
    void save() {
        String name = "test";
        Book expectedBook = bookRepository.save(new Book(null, name));
        checkSelect(expectedBook);
    }

    @Test
    void update() {
        Book update = new Book(
                bookRepository.save(new Book(null, "save")).getId(),
                "test2"
        );
        bookRepository.save(new Book(update.getId(),"test2"));
        checkSelect(update);
    }

    @Test
    void delete() {
        Long id = bookRepository.save(new Book(null, "test")).getId();
        bookRepository.deleteById(id);
        assertNull(bookRepository.findById(id).orElse(null));
    }

    @Test
    void selectAll() {
        Book expected3 = bookRepository.save(new Book(null, "test3"));
        Book expected4 = bookRepository.save(new Book(null, "test4"));
        Book expected5 = bookRepository.save(new Book(null, "test5"));

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