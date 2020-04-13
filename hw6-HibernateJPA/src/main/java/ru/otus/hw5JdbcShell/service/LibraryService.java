package ru.otus.hw5JdbcShell.service;

import org.springframework.stereotype.Service;
import ru.otus.hw5JdbcShell.model.dto.Book;
import ru.otus.hw5JdbcShell.model.view.LibraryRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class LibraryService {
    private final BookService bookService;
    private final GenreService genreService;
    private final AuthorService authorService;
    private final BookGenreService bookGenreService;
    private final BookAuthorService bookAuthorService;

    public LibraryService(
            BookService bookService,
            GenreService genreService,
            AuthorService authorService,
            BookGenreService bookGenreService,
            BookAuthorService bookAuthorService
    ) {
        this.bookService = bookService;
        this.genreService = genreService;
        this.authorService = authorService;
        this.bookGenreService = bookGenreService;
        this.bookAuthorService = bookAuthorService;
    }

    public Long add(String name, Set<String> authors, Set<String> genres) {
        Long bookId = bookService.add(name);
        Set<Long> authorIds = authorService.add(authors);
        Set<Long> genreIds = genreService.add(genres);
        bookAuthorService.add(bookId, authorIds);
        bookGenreService.add(bookId, genreIds);
        return bookId;
    }

    public LibraryRecord get(Long bookId) {
        Book book = bookService.get(bookId);
        if (book != null) {
            return createLibraryRecord(book);
        }
        return null;
    }

    private LibraryRecord createLibraryRecord(Book book) {
        Set<Long> authorIds = bookAuthorService.getAuthorIds(book.getId());
        Set<Long> genreIds = bookGenreService.getGenreIds(book.getId());
        return new LibraryRecord(
                book.getId(),
                book.getName(),
                authorService.getByIds(authorIds),
                genreService.getByIds(genreIds)
        );
    }

    public void update(Long bookId, String name, Set<String> authors, Set<String> genres) {
        bookService.update(bookId, name);

        Set<Long> authorIds = authorService.add(authors);
        bookAuthorService.update(bookId, authorIds);

        Set<Long> genreIds = genreService.add(genres);
        bookGenreService.update(bookId, genreIds);
    }

    public void remove(Long bookId) {
        bookAuthorService.delete(bookId);
        bookGenreService.delete(bookId);
        bookService.delete(bookId);
    }

    public List<LibraryRecord> getAll() {
        List<LibraryRecord> libraryRecords = new ArrayList<>();
        for (Book book : bookService.getAll()) {
            libraryRecords.add(
                    createLibraryRecord(book)
            );
        }
        return libraryRecords;
    }
}
