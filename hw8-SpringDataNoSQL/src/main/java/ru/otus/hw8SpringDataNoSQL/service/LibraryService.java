package ru.otus.hw8SpringDataNoSQL.service;

import org.springframework.stereotype.Service;
import ru.otus.hw8SpringDataNoSQL.model.Author;
import ru.otus.hw8SpringDataNoSQL.model.Book;
import ru.otus.hw8SpringDataNoSQL.model.Genre;

import java.util.List;
import java.util.Set;

@Service
public class LibraryService {
    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final CommentaryService commentaryService;

    public LibraryService(
            BookService bookService,
            GenreService genreService,
            AuthorService authorService,
            CommentaryService commentaryService) {
        this.bookService = bookService;
        this.genreService = genreService;
        this.authorService = authorService;
        this.commentaryService = commentaryService;
    }

    public Long add(String name, Set<String> authors, Set<String> genres) {
        List<Author> authorList = authorService.add(authors);
        List<Genre> genreList = genreService.add(genres);
        return bookService.add(new Book(name, null, authorList, genreList, null));
    }

    public void addCommentary(Long bookId, String text) {
        bookService.addCommentary(
                bookId, commentaryService.add(text)
        );
    }

    public Book get(Long bookId) {
        return bookService.get(bookId);
    }

    public void update(Long bookId, String name, Set<String> authors, Set<String> genres) {
        List<Author> authorList = authorService.add(authors);
        List<Genre> genreList = genreService.add(genres);
        bookService.update(bookId, name, authorList, genreList);
    }

    public void remove(Long bookId) {
        bookService.delete(bookId);
    }

    public List<Book> getAll() {
        return bookService.getAll();
    }
}
