package ru.otus.hw8SpringDataNoSQL.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw8SpringDataNoSQL.model.Author;
import ru.otus.hw8SpringDataNoSQL.model.Book;
import ru.otus.hw8SpringDataNoSQL.model.Genre;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
@DataMongoTest
@Import(
        value = {
                BookService.class,
                GenreService.class,
                AuthorService.class,
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
    void addCommentary() {
        String id = libraryService.add(
                "name",
                Collections.singleton("author"),
                Collections.singleton("genre")
        );
        libraryService.addCommentary(id, "text");
        libraryService.addCommentary(id, "text");
        libraryService.addCommentary(id, "text2");
        List<String> commentaries = libraryService.get(id).getCommentaries();
        assertEquals(3, commentaries.size());
        List<String> expectedTexts = new ArrayList<>();
        expectedTexts.add("text");
        expectedTexts.add("text2");
        Collections.sort(expectedTexts);
        assertEquals(expectedTexts, commentaries);
    }

    @Test
    void add() {
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String authorName = UUID.randomUUID().toString();
            authors.add(authorService.add(authorName));
        }


        List<Genre> genres = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String genreName = UUID.randomUUID().toString();
            genres.add(genreService.add(genreName));
        }

        String bookName = "add";
        String bookId = libraryService.add(
                bookName,
                authors.stream()
                        .map(Author::getName)
                        .collect(Collectors.toSet()),
                genres.stream()
                        .map(Genre::getName)
                        .collect(Collectors.toSet())
        );
        Book expectedBook = new Book(
                bookName,
                bookId,
                authors,
                genres,
                null
        );

        assertEquals(expectedBook, libraryService.get(bookId));
    }

    @Test
    void update() {

        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String authorName = UUID.randomUUID().toString();
            authors.add(authorService.add(authorName));
        }

        Author everyWhereAuthor = authorService.add("everyWhereAuthor");
        authors.add(everyWhereAuthor);

        List<Genre> genres = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String genreName = UUID.randomUUID().toString();
            genres.add(genreService.add(genreName));
        }

        Genre everyWhereGenre = genreService.add("everyWhereGenre");
        genres.add(everyWhereGenre);

        String bookName = "updateLibraryRecordTest";
        String bookId = libraryService.add(
                bookName,
                authors.stream()
                        .map(Author::getName)
                        .collect(Collectors.toSet()),
                genres.stream()
                        .map(Genre::getName)
                        .collect(Collectors.toSet())
        );

        List<Author> expectedAuthors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String expectedAuthorName = UUID.randomUUID().toString();
            expectedAuthors.add(authorService.add(expectedAuthorName));
        }
        expectedAuthors.add(everyWhereAuthor);

        List<Genre> expectedGenres = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String expectedGenreName = UUID.randomUUID().toString();
            expectedGenres.add(genreService.add(expectedGenreName));
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
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String authorName = UUID.randomUUID().toString();
            authors.add(authorService.add(authorName));
        }


        List<Genre> genres = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String genreName = UUID.randomUUID().toString();
            genres.add(genreService.add(genreName));
        }

        String bookName = "addLibraryRecordTest";
        String bookId = libraryService.add(
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
        List<Book> expected = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            List<Author> authors = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                String authorName = UUID.randomUUID().toString();
                authors.add(authorService.add(authorName));
            }


            List<Genre> genres = new ArrayList<>();
            for (int i = 0; i < 4; i++) {
                String genreName = UUID.randomUUID().toString();
                genres.add(genreService.add(genreName));
            }

            String bookName = UUID.randomUUID().toString();
            String bookId = libraryService.add(
                    bookName,
                    authors.stream()
                            .map(Author::getName)
                            .collect(Collectors.toSet()),
                    genres.stream()
                            .map(Genre::getName)
                            .collect(Collectors.toSet())
            );
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