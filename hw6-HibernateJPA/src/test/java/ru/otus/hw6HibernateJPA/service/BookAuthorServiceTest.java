package ru.otus.hw6HibernateJPA.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw6HibernateJPA.repository.AuthorRepository;
import ru.otus.hw6HibernateJPA.repository.BookRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@JdbcTest
@Import(
        value = {
                BookAuthorService.class,
                BookAuthorRepository.class,
                BookRepository.class,
                AuthorRepository.class
        }
)
class BookAuthorServiceTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookAuthorService bookAuthorService;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void add() {
        Long bookId = bookRepository.insert("test");
        Set<Long> expectedAuthorIds = new HashSet<>();
        expectedAuthorIds.add(authorRepository.insert(UUID.randomUUID().toString()));
        expectedAuthorIds.add(authorRepository.insert(UUID.randomUUID().toString()));
        expectedAuthorIds.add(authorRepository.insert(UUID.randomUUID().toString()));
        bookAuthorService.add(bookId, expectedAuthorIds);
        assertEquals(expectedAuthorIds, bookAuthorService.getAuthorIds(bookId));
    }

    @Test
    void update() {
        Long bookId = bookRepository.insert("test");
        Set<Long> authorIds = new HashSet<>();
        Long authorId = authorRepository.insert(UUID.randomUUID().toString());
        authorIds.add(authorId);
        authorIds.add(authorRepository.insert(UUID.randomUUID().toString()));
        authorIds.add(authorRepository.insert(UUID.randomUUID().toString()));
        bookAuthorService.add(bookId, authorIds);

        Set<Long> expectedAuthorIds = new HashSet<>();
        expectedAuthorIds.add(authorId);
        authorIds.add(authorRepository.insert(UUID.randomUUID().toString()));
        authorIds.add(authorRepository.insert(UUID.randomUUID().toString()));
        bookAuthorService.update(bookId, expectedAuthorIds);

        assertEquals(expectedAuthorIds, bookAuthorService.getAuthorIds(bookId));
    }

    @Test
    void delete() {
        Long bookId = bookRepository.insert("test");
        Set<Long> expectedAuthorIds = new HashSet<>();
        expectedAuthorIds.add(authorRepository.insert(UUID.randomUUID().toString()));
        expectedAuthorIds.add(authorRepository.insert(UUID.randomUUID().toString()));
        expectedAuthorIds.add(authorRepository.insert(UUID.randomUUID().toString()));
        bookAuthorService.add(bookId, expectedAuthorIds);
        bookAuthorService.delete(bookId);
        assertEquals(0, bookAuthorService.getAuthorIds(bookId).size());
    }
}