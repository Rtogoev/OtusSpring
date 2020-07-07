package ru.otus.hw14SpringBatch.service;

import org.springframework.stereotype.Service;
import ru.otus.hw14SpringBatch.model.Author;
import ru.otus.hw14SpringBatch.model.Book;
import ru.otus.hw14SpringBatch.model.Genre;
import ru.otus.hw14SpringBatch.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book get(String id) {
        return bookRepository.findById(id)
                .orElse(null);
    }

    public void update(String id, String name, List<Author> authorList, List<Genre> genreList) {
        Book forUpdate = get(id);
        bookRepository.save(
                new Book(
                        name,
                        forUpdate.getId(),
                        authorList,
                        genreList,
                        forUpdate.getCommentaries()
                )
        );
    }

    public void addCommentary(String id, String commentary) {
        Book forUpdate = get(id);
        bookRepository.save(
                new Book(
                        forUpdate.getName(),
                        forUpdate.getId(),
                        forUpdate.getAuthors(),
                        forUpdate.getGenres(),
                        updateCommentaries(forUpdate.getCommentaries(), commentary)
                )
        );
    }

    private static List<String> updateCommentaries(List<String> commentaries, String commentary) {
        if (commentaries == null) {
            List<String> newCommentaries = new ArrayList<>();
            newCommentaries.add(commentary);
            return newCommentaries;
        }
        commentaries.add(commentary);
        return commentaries;
    }

    public void delete(String id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public String add(Book book) {
        return bookRepository.save(book).getId();
    }
}
