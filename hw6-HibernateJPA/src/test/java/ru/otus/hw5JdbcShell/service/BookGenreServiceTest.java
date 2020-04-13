package ru.otus.hw5JdbcShell.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw5JdbcShell.repository.BookGenreRepository;
import ru.otus.hw5JdbcShell.repository.BookRepository;
import ru.otus.hw5JdbcShell.repository.GenreRepository;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


@JdbcTest
@Import(
        value = {
                BookGenreService.class,
                BookGenreRepository.class,
                BookRepository.class,
                GenreRepository.class
        }
)
class BookGenreServiceTest {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookGenreService bookGenreService;
    @Autowired
    private GenreRepository genreRepository;

    @Test
    void add() {
        Long bookId = bookRepository.insert("test");
        Set<Long> expectedGenreIds = new HashSet<>();
        expectedGenreIds.add(genreRepository.insert(UUID.randomUUID().toString()));
        expectedGenreIds.add(genreRepository.insert(UUID.randomUUID().toString()));
        expectedGenreIds.add(genreRepository.insert(UUID.randomUUID().toString()));
        bookGenreService.add(bookId, expectedGenreIds);
        assertEquals(expectedGenreIds, bookGenreService.getGenreIds(bookId));
    }

    @Test
    void update() {
        Long bookId = bookRepository.insert("test");
        Set<Long> genreIds = new HashSet<>();
        Long genreId = genreRepository.insert(UUID.randomUUID().toString());
        genreIds.add(genreId);
        genreIds.add(genreRepository.insert(UUID.randomUUID().toString()));
        genreIds.add(genreRepository.insert(UUID.randomUUID().toString()));
        bookGenreService.add(bookId, genreIds);

        Set<Long> expectedGenreIds = new HashSet<>();
        expectedGenreIds.add(genreId);
        genreIds.add(genreRepository.insert(UUID.randomUUID().toString()));
        genreIds.add(genreRepository.insert(UUID.randomUUID().toString()));
        bookGenreService.update(bookId, expectedGenreIds);

        assertEquals(expectedGenreIds, bookGenreService.getGenreIds(bookId));
    }

    @Test
    void delete() {
        Long bookId = bookRepository.insert("test");
        Set<Long> expectedGenreIds = new HashSet<>();
        expectedGenreIds.add(genreRepository.insert(UUID.randomUUID().toString()));
        expectedGenreIds.add(genreRepository.insert(UUID.randomUUID().toString()));
        expectedGenreIds.add(genreRepository.insert(UUID.randomUUID().toString()));
        bookGenreService.add(bookId, expectedGenreIds);
        bookGenreService.delete(bookId);
        assertEquals(0, bookGenreService.getGenreIds(bookId).size());
    }
}