package ru.otus.hw18Hystrix.service;

import org.springframework.stereotype.Service;
import ru.otus.hw18Hystrix.model.Author;
import ru.otus.hw18Hystrix.model.Book;
import ru.otus.hw18Hystrix.model.Genre;

import java.util.List;
import java.util.Set;

@Service
public class LibraryService {
    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;

    public LibraryService(
            BookService bookService,
            GenreService genreService,
            AuthorService authorService
    ) {
        this.bookService = bookService;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    public String add(String name, Set<String> authors, Set<String> genres) {
        List<Author> authorList = authorService.add(authors);
        List<Genre> genreList = genreService.add(genres);
        return bookService.add(new Book(name, null, authorList, genreList, null));
    }

    public void addCommentary(String bookId, String text) {
        bookService.addCommentary(bookId, text);
    }

    public Book get(String bookId) {
        return bookService.get(bookId);
    }

    public void update(String bookId, String name, Set<String> authors, Set<String> genres) {
        List<Author> authorList = authorService.add(authors);
        List<Genre> genreList = genreService.add(genres);
        bookService.update(bookId, name, authorList, genreList);
    }

    public void remove(String bookId) {
        bookService.delete(bookId);
    }

    public List<Book> getAll() {
        return bookService.getAll();
    }
}
