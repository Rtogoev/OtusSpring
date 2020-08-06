package ru.otus.hw18Hystrix.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import ru.otus.hw18Hystrix.model.Author;
import ru.otus.hw18Hystrix.model.Book;
import ru.otus.hw18Hystrix.model.Genre;
import ru.otus.hw18Hystrix.repository.BookRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BookService {

    private static final String UNKNOWN = "unknown";
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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

    @HystrixCommand(
             commandProperties = {
                    @HystrixProperty(
                            name = "execution.isolation.thread.timeoutInMilliseconds", value = "1000"
                    )
            }
    )
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

    public void delete(String id) {
        bookRepository.deleteById(id);
    }

    public List<Book> getFallbackBooks() {
        return Collections.singletonList(
                new Book(
                        UNKNOWN,
                        UNKNOWN,
                        Collections.singletonList(
                                new Author(
                                        UNKNOWN,
                                        UNKNOWN
                                )
                        ),
                        Collections.singletonList(
                                new Genre(
                                        UNKNOWN,
                                        UNKNOWN
                                )
                        ),
                        Collections.singletonList(UNKNOWN)
                )
        );
    }

    @HystrixCommand(fallbackMethod = "getFallbackBooks")
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public String add(Book book) {
        return bookRepository.save(book).getId();
    }
}
