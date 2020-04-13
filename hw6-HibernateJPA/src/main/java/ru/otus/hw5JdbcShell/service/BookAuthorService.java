package ru.otus.hw5JdbcShell.service;

import org.springframework.stereotype.Service;
import ru.otus.hw5JdbcShell.repository.BookAuthorRepository;

import java.util.Set;

@Service
public class BookAuthorService {
    private final BookAuthorRepository bookAuthorRepository;

    public BookAuthorService(BookAuthorRepository bookAuthorRepository) {
        this.bookAuthorRepository = bookAuthorRepository;
    }

    public void add(Long bookId, Set<Long> authorIds) {
        for (Long authorId : authorIds) {
            bookAuthorRepository.insert(bookId, authorId);
        }
    }

    public Set<Long> getAuthorIds(Long bookId) {
        return bookAuthorRepository.select(bookId);
    }

    public void update(Long bookId, Set<Long> authorIds) {
        delete(bookId);
        add(bookId, authorIds);
    }

    public void delete(Long bookId) {
        bookAuthorRepository.delete(bookId);
    }
}
