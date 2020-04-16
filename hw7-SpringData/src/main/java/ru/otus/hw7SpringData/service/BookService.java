package ru.otus.hw7SpringData.service;

import org.springframework.stereotype.Service;
import ru.otus.hw7SpringData.model.Author;
import ru.otus.hw7SpringData.model.Book;
import ru.otus.hw7SpringData.model.Commentary;
import ru.otus.hw7SpringData.model.Genre;
import ru.otus.hw7SpringData.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book get(Long id) {
        return bookRepository.select(id);
    }

    public void update(Long id, String name, List<Author> authorList, List<Genre> genreList) {
        Book forUpdate = get(id);
        bookRepository.update(
                new Book(
                        name,
                        forUpdate.getId(),
                        authorList,
                        genreList,
                        forUpdate.getCommentaries()
                )
        );
    }

    public void addCommentary(Long id, Commentary commentary) {
        Book forUpdate = get(id);
        bookRepository.update(
                new Book(
                        forUpdate.getName(),
                        forUpdate.getId(),
                        forUpdate.getAuthors(),
                        forUpdate.getGenres(),
                        updateCommentaries(forUpdate.getCommentaries(), commentary)
                )
        );
    }

    private static List<Commentary> updateCommentaries(List<Commentary> commentaries, Commentary commentary) {
        if (commentaries == null) {
            List<Commentary> newCommentaries = new ArrayList<>();
            newCommentaries.add(commentary);
            return newCommentaries;
        }
        commentaries.add(commentary);
        return commentaries;
    }

    public void delete(Long id) {
        bookRepository.delete(id);
    }

    public List<Book> getAll() {
        return bookRepository.selectAll();
    }

    public Long add(Book book) {
        return bookRepository.insert(book);
    }
}
