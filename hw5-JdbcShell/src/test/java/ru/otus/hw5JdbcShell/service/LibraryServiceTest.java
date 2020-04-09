package ru.otus.hw5JdbcShell.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw5JdbcShell.model.dto.Author;
import ru.otus.hw5JdbcShell.model.dto.Genre;
import ru.otus.hw5JdbcShell.model.view.LibraryRecord;
import ru.otus.hw5JdbcShell.repository.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(
        value = {
                BookService.class,
                BookRepository.class,
                GenreService.class,
                GenreRepository.class,
                AuthorService.class,
                AuthorRepository.class,
                BookGenreService.class,
                BookGenreRepository.class,
                BookAuthorService.class,
                BookAuthorRepository.class,
                LibraryService.class
        }
)
class LibraryServiceTest {
    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private LibraryService libraryService;

    @Test
    void add() {
        Set<Author> authors = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            String authorName = UUID.randomUUID().toString();
            authors.add(new Author(authorService.add(authorName), authorName));
        }


        Set<Genre> genres = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            String genreName = UUID.randomUUID().toString();
            genres.add(new Genre(genreService.add(genreName), genreName));
        }

        String bookName = "addLibraryRecordTest";
        Long bookId = libraryService.add(
                bookName,
                authors.stream()
                        .map(Author::getName)
                        .collect(Collectors.toSet()),
                genres.stream()
                        .map(Genre::getName)
                        .collect(Collectors.toSet())
        );
        LibraryRecord expectedLibraryRecord = new LibraryRecord(
                bookId,
                bookName,
                new ArrayList<>(authors),
                new ArrayList<>(genres)
        );

        assertEquals(expectedLibraryRecord, libraryService.get(bookId));
    }

    @Test
    void update() {

        Set<Author> authors = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            String authorName = UUID.randomUUID().toString();
            authors.add(new Author(authorService.add(authorName), authorName));
        }

        Author everyWhereAuthor = new Author(authorService.add("everyWhereAuthor"), "everyWhereAuthor");
        authors.add(everyWhereAuthor);

        Set<Genre> genres = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            String genreName = UUID.randomUUID().toString();
            genres.add(new Genre(genreService.add(genreName), genreName));
        }

        Genre everyWhereGenre = new Genre(genreService.add("everyWhereGenre"), "everyWhereGenre");
        genres.add(everyWhereGenre);

        String bookName = "updateLibraryRecordTest";
        Long bookId = libraryService.add(
                bookName,
                authors.stream()
                        .map(Author::getName)
                        .collect(Collectors.toSet()),
                genres.stream()
                        .map(Genre::getName)
                        .collect(Collectors.toSet())
        );

        Set<Author> expectedAuthors = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            String expectedAuthorName = UUID.randomUUID().toString();
            expectedAuthors.add(new Author(authorService.add(expectedAuthorName), expectedAuthorName));
        }
        expectedAuthors.add(everyWhereAuthor);

        Set<Genre> expectedGenres = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            String expectedGenreName = UUID.randomUUID().toString();
            expectedGenres.add(new Genre(genreService.add(expectedGenreName), expectedGenreName));
        }
        expectedGenres.add(everyWhereGenre);

        String expectedBookName = "updateLibraryRecordTest2";
        libraryService.update(
                bookId,
                expectedBookName,
                expectedAuthors.stream()
                        .map(Author::getName)
                        .collect(Collectors.toSet()),
                expectedGenres.stream()
                        .map(Genre::getName)
                        .collect(Collectors.toSet())
        );

        LibraryRecord expectedLibraryRecord = new LibraryRecord(
                bookId,
                expectedBookName,
                new ArrayList<>(expectedAuthors),
                new ArrayList<>(expectedGenres)
        );
        assertEquals(expectedLibraryRecord, libraryService.get(bookId));
        assertEquals(expectedLibraryRecord, libraryService.get(bookId));
    }

    @Test
    void remove() {
        Set<Author> authors = new HashSet<>();
        for (int i = 0; i < 3; i++) {
            String authorName = UUID.randomUUID().toString();
            authors.add(new Author(authorService.add(authorName), authorName));
        }


        Set<Genre> genres = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            String genreName = UUID.randomUUID().toString();
            genres.add(new Genre(genreService.add(genreName), genreName));
        }

        String bookName = "addLibraryRecordTest";
        Long bookId = libraryService.add(
                bookName,
                authors.stream()
                        .map(Author::getName)
                        .collect(Collectors.toSet()),
                genres.stream()
                        .map(Genre::getName)
                        .collect(Collectors.toSet())
        );
        libraryService.remove(bookId);
        assertNull(libraryService.get(bookId));
    }

    @Test
    void getAll() {
        List<LibraryRecord> expected = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            Set<Author> authors = new HashSet<>();
            for (int i = 0; i < 3; i++) {
                String authorName = UUID.randomUUID().toString();
                authors.add(new Author(authorService.add(authorName), authorName));
            }


            Set<Genre> genres = new HashSet<>();
            for (int i = 0; i < 4; i++) {
                String genreName = UUID.randomUUID().toString();
                genres.add(new Genre(genreService.add(genreName), genreName));
            }

            String bookName = UUID.randomUUID().toString();
            Long bookId = libraryService.add(
                    bookName,
                    authors.stream()
                            .map(Author::getName)
                            .collect(Collectors.toSet()),
                    genres.stream()
                            .map(Genre::getName)
                            .collect(Collectors.toSet())
            );
            expected.add(
                    new LibraryRecord(
                            bookId,
                            bookName,
                            new ArrayList<>(authors),
                            new ArrayList<>(genres)
                    )
            );
        }
        List<LibraryRecord> actual = libraryService.getAll();

        assertEquals(actual.size(), expected.size());

        Set<LibraryRecord> actualSet = new HashSet<>(actual);
        actualSet.removeAll(expected);

        Set<LibraryRecord> expectedSet = new HashSet<>(expected);
        expectedSet.removeAll(actual);

        assertTrue(actualSet.size() == 0 && expectedSet.size() == 0);
    }
}