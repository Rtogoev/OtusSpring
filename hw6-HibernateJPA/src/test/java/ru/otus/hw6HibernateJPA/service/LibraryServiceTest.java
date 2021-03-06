package ru.otus.hw6HibernateJPA.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw6HibernateJPA.model.Author;
import ru.otus.hw6HibernateJPA.model.Book;
import ru.otus.hw6HibernateJPA.model.Commentary;
import ru.otus.hw6HibernateJPA.model.Genre;
import ru.otus.hw6HibernateJPA.repository.AuthorRepository;
import ru.otus.hw6HibernateJPA.repository.BookRepository;
import ru.otus.hw6HibernateJPA.repository.CommentaryRepository;
import ru.otus.hw6HibernateJPA.repository.GenreRepository;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import(
        value = {
                AuthorRepository.class,
                GenreRepository.class,
                BookRepository.class,
                CommentaryRepository.class,
                BookService.class,
                GenreService.class,
                AuthorService.class,
                CommentaryService.class,
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
        Long id = libraryService.add(
                "name",
                Collections.singleton("author"),
                Collections.singleton("genre")
        );
        libraryService.addCommentary(id, "text");
        libraryService.addCommentary(id, "text");
        libraryService.addCommentary(id, "text2");
        List<Commentary> commentaries = libraryService.get(id).getCommentaries();
        assertEquals(3, commentaries.size());
        List<String> expectedTexts = new ArrayList<>();
        expectedTexts.add("text");
        expectedTexts.add("text2");
        Collections.sort(expectedTexts);
        List<String> actualTexts = commentaries.stream()
                .map(Commentary::getText)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        assertEquals(expectedTexts, actualTexts);
    }

        @Test
    void add() {
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String authorName = UUID.randomUUID().toString();
            authors.add(new Author(authorService.add(authorName), authorName));
        }


        List<Genre> genres = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            String genreName = UUID.randomUUID().toString();
            genres.add(new Genre(genreService.add(genreName), genreName));
        }

        String bookName = "add";
        Long bookId = libraryService.add(
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
            authors.add(new Author(authorService.add(authorName), authorName));
        }

        Author everyWhereAuthor = new Author(authorService.add("everyWhereAuthor"), "everyWhereAuthor");
        authors.add(everyWhereAuthor);

        List<Genre> genres = new ArrayList<>();
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

        List<Author> expectedAuthors = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String expectedAuthorName = UUID.randomUUID().toString();
            expectedAuthors.add(new Author(authorService.add(expectedAuthorName), expectedAuthorName));
        }
        expectedAuthors.add(everyWhereAuthor);

        List<Genre> expectedGenres = new ArrayList<>();
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
            authors.add(new Author(authorService.add(authorName), authorName));
        }


        List<Genre> genres = new ArrayList<>();
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
        List<Book> expected = new ArrayList<>();
        for (int j = 0; j < 3; j++) {
            List<Author> authors = new ArrayList<>();
            for (int i = 0; i < 3; i++) {
                String authorName = UUID.randomUUID().toString();
                authors.add(new Author(authorService.add(authorName), authorName));
            }


            List<Genre> genres = new ArrayList<>();
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