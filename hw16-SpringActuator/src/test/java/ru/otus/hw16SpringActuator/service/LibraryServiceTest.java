package ru.otus.hw16SpringActuator.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.hw16SpringActuator.model.Author;
import ru.otus.hw16SpringActuator.model.Book;
import ru.otus.hw16SpringActuator.model.Genre;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryServiceTest {
    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private LibraryService libraryService;

    @Test
    void addCommentary() {
        String id = libraryService.add(
                "name",
                Collections.singleton("author"),
                Collections.singleton("genre")
        );

        List<String> expectedTexts = new ArrayList<>();
        expectedTexts.add("text");
        expectedTexts.add("text");
        expectedTexts.add("text2");
        expectedTexts.forEach(text -> libraryService.addCommentary(id, text));

        List<String> commentaries = libraryService.get(id).getCommentaries();
        assertEquals(3, commentaries.size());

        Collections.sort(expectedTexts);

        assertEquals(expectedTexts, commentaries);
    }

    @Test
    void add() {
        List<Author> authors = generateAuthors();
        List<Genre> genres = generateGenres();

        String bookName = "add";
        String bookId = addBook(bookName, authors, genres);
        Book expectedBook = new Book(
                bookName,
                bookId,
                authors,
                genres,
                null
        );

        assertEquals(expectedBook, libraryService.get(bookId));
    }

    private String addBook(
            String bookName,
            List<Author> authors,
            List<Genre> genres
    ) {
        return libraryService.add(
                bookName,
                getAuthorNames(authors),
                getGenreNames(genres)
        );
    }

    private Set<String> getGenreNames(List<Genre> genres) {
        return genres.stream()
                .map(Genre::getName)
                .collect(Collectors.toSet());
    }

    private Set<String> getAuthorNames(List<Author> authors) {
        return authors.stream()
                .map(Author::getName)
                .collect(Collectors.toSet());
    }

    private List<Genre> generateGenres() {
        List<Genre> genres = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String genreName = UUID.randomUUID().toString();
            genres.add(genreService.add(genreName));
        }
        return genres;
    }

    private List<Author> generateAuthors() {
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String authorName = UUID.randomUUID().toString();
            authors.add(authorService.add(authorName));
        }
        return authors;
    }

    @Test
    void update() {
        List<Author> authors = generateAuthors();
        Author everyWhereAuthor = authorService.add("everyWhereAuthor");
        authors.add(everyWhereAuthor);
        List<Genre> genres = generateGenres();

        Genre everyWhereGenre = genreService.add("everyWhereGenre");
        genres.add(everyWhereGenre);

        String bookName = "updateLibraryRecordTest";
        String bookId = addBook(bookName, authors, genres);

        List<Author> expectedAuthors = generateAuthors();
        expectedAuthors.add(everyWhereAuthor);
        List<Genre> expectedGenres = generateGenres();
        expectedGenres.add(everyWhereGenre);

        String expectedBookName = "updateLibraryRecordTest2";
        libraryService.update(
                bookId,
                expectedBookName,
                getAuthorNames(expectedAuthors),
                getGenreNames(expectedGenres)
        );

        Book expectedBook = new Book(
                expectedBookName,
                bookId,
                expectedAuthors,
                expectedGenres,
                null
        );
        assertEquals(expectedBook, libraryService.get(bookId));
    }

    @Test
    void remove() {
        List<Author> authors = generateAuthors();
        List<Genre> genres = generateGenres();

        String bookName = "addLibraryRecordTest";
        String bookId = addBook(bookName, authors, genres);
        libraryService.remove(bookId);
        assertNull(libraryService.get(bookId));
    }

    @Test
    void getAll() {
        List<Book> expected = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            List<Author> authors = generateAuthors();
            List<Genre> genres = generateGenres();

            String bookName = UUID.randomUUID().toString();
            String bookId = addBook(bookName, authors, genres);
            expected.add(
                    new Book(
                            bookName,
                            bookId,
                            authors,
                            genres,
                            null
                    )
            );
        }
        List<Book> actual = libraryService.getAll();

        assertEquals(actual.size(), expected.size());

        Set<Book> actualSet = new HashSet<>(actual);
        actualSet.removeAll(expected);

        Set<Book> expectedSet = new HashSet<>(expected);
        expectedSet.removeAll(actual);

        assertTrue(actualSet.size() == 0 && expectedSet.size() == 0);
    }
}