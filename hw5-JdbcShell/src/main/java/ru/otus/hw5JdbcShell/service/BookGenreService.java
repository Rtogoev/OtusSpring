package ru.otus.hw5JdbcShell.service;

import org.springframework.stereotype.Service;
import ru.otus.hw5JdbcShell.repository.BookGenreRepository;

import java.util.Set;

@Service
public class BookGenreService {
    private final BookGenreRepository bookGenreRepository;

    public BookGenreService(BookGenreRepository bookGenreRepository) {
        this.bookGenreRepository = bookGenreRepository;
    }

    public void add(Long bookId, Set<Long> genreIds) {
        for (Long genreId : genreIds) {
            bookGenreRepository.insert(bookId, genreId);
        }
    }

    public Set<Long> getGenreIds(Long bookId) {
        return bookGenreRepository.select(bookId);
    }

    public void update(Long bookId, Set<Long> genreIds) {
        delete(bookId);
        add(bookId, genreIds);
    }

    public void delete(Long bookId) {
        bookGenreRepository.delete(bookId);
    }
}
