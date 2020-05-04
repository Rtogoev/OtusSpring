package ru.otus.hw9SpringMVC.service;

import org.springframework.stereotype.Service;
import ru.otus.hw9SpringMVC.model.Author;
import ru.otus.hw9SpringMVC.model.Book;
import ru.otus.hw9SpringMVC.model.BookForm;
import ru.otus.hw9SpringMVC.model.Genre;
import ru.otus.hw9SpringMVC.utils.Utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookFormService {

    private final LibraryService libraryService;

    public BookFormService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void add(BookForm bookForm) {
        add(
                bookForm.getName(),
                bookForm.getAuthorsString(),
                bookForm.getGenresString()
        );
    }

    private void add(String name, String authors, String genres) {
        Set<String> authorList = Utils.split(authors);
        Set<String> genreList = Utils.split(genres);
        libraryService.add(name, authorList, genreList);
    }

    public void update(String id, BookForm bookForm) {
        update(
                id,
                bookForm.getName(),
                bookForm.getAuthorsString(),
                bookForm.getGenresString()
        );
    }

    private void update(String id, String name, String authors, String genres) {
        Set<String> authorList = Utils.split(authors);
        Set<String> genreList = Utils.split(genres);
        libraryService.update(id, name, authorList, genreList);
    }


    public List<BookForm> getAll() {
        return libraryService.getAll()
                .stream()
                .map(this::toBookForm)
                .collect(Collectors.toList());
    }

    private BookForm toBookForm(Book book) {
        String authorsString = book.getAuthors()
                .stream()
                .map(Author::getName)
                .collect(Collectors.joining(","));

        String genresString = book.getGenres()
                .stream()
                .map(Genre::getName)
                .collect(Collectors.joining(","));

        return new BookForm(
                book.getId(),
                book.getName(),
                authorsString,
                genresString,
                joinIfNotNull(book.getCommentaries())
        );
    }

    private String joinIfNotNull(List<String> strings) {
        if (strings == null) {
            return "";
        }

        return String.join(",", strings);
    }

    public BookForm get(String id) {
        return toBookForm(libraryService.get(id));
    }
}
