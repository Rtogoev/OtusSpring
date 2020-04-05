package ru.otus.hw5JdbcShell.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw5JdbcShell.model.Book;
import ru.otus.hw5JdbcShell.repository.AuthorDtoRepository;
import ru.otus.hw5JdbcShell.repository.BookDtoRepository;
import ru.otus.hw5JdbcShell.repository.GenreDtoRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@Import(
        value = {
                BookService.class,
                GenreService.class,
                AuthorService.class,
                BookDtoRepository.class,
                GenreDtoRepository.class,
                AuthorDtoRepository.class
        }
)
class BookServiceTest {
    @Autowired
    private BookService bookService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private AuthorService authorService;

    @Test
    void testAll() {
        String name = "name";
        List<String> authors = new ArrayList<>();
        authors.add("author1");
        authors.add("author2");

        List<String> genres = new ArrayList<>();
        genres.add("genre1");
        genres.add("genre2");
        Book expectedBook = new Book(
                bookService.add(name, authors, genres),
                name,
                authorService.getByNames(authors),
                genreService.getByNames(genres)
        );

        assertEquals(expectedBook, bookService.get(expectedBook.getId()));

        authors.remove(0);
        authors.add("author3");
        genres.remove(0);
        genres.add("genre3");

        bookService.update(
                expectedBook.getId(),
                "newName",
                authors,
                genres
        );

        expectedBook = new Book(
                expectedBook.getId(),
                "newName",
                authorService.getByNames(authors),
                genreService.getByNames(genres)
        );

        assertEquals(expectedBook, bookService.get(expectedBook.getId()));

        bookService.remove(expectedBook.getId());

        Book book1 = new Book(
                bookService.add(
                        "name1",
                        Collections.singletonList("author1"),
                        Collections.singletonList("genre1")
                ),
                "name1",
                Collections.singletonList(authorService.get("author1")),
                Collections.singletonList(genreService.get("genre1"))
        );
        Book book2 = new Book(
                bookService.add("name2", Collections.singletonList("author2"), Collections.singletonList("genre2")),
                "name2",
                Collections.singletonList(authorService.get("author2")),
                Collections.singletonList(genreService.get("genre2"))
        );

        Book book3 = new Book(
                bookService.add("name3", Collections.singletonList("author3"), Collections.singletonList("genre3")),
                "name3",
                Collections.singletonList(authorService.get("author3")),
                Collections.singletonList(genreService.get("genre3"))
        );
        List<Book> books = bookService.getAll();

        assertFalse(books.contains(expectedBook));
        assertTrue(books.contains(book1));
        assertTrue(books.contains(book2));
        assertTrue(books.contains(book3));
    }

}