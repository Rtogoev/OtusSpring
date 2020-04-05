package ru.otus.hw5JdbcShell.service;

import org.springframework.stereotype.Service;
import ru.otus.hw5JdbcShell.model.Book;
import ru.otus.hw5JdbcShell.model.dto.BookDto;
import ru.otus.hw5JdbcShell.repository.BookDtoRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class BookService {
    private final BookDtoRepository bookDtoRepository;
    private final GenreService genreService;
    private final AuthorService authorService;

    public BookService(BookDtoRepository bookDtoRepository, GenreService genreService, AuthorService authorService) {
        this.bookDtoRepository = bookDtoRepository;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    public Long add(String name, Set<String> authors, Set<String> genres) {
        return bookDtoRepository.insert(
                name,
                authorService.add(authors),
                genreService.add(genres)
        );
    }


    public Book get(Long id) {
        BookDto bookDto = bookDtoRepository.select(id);
        return new Book(
                bookDto.getId(),
                bookDto.getName(),
                authorService.getByIds(bookDto.getAuthorIds()),
                genreService.getByIds(bookDto.getGenreIds())
        );
    }

    public void update(Long id, String name, Set<String> authors, Set<String> genres) {
        bookDtoRepository.update(
                id,
                name,
                authorService.add(authors),
                genreService.add(genres)
        );
    }

    public void remove(Long id) {
        bookDtoRepository.delete(id);
    }

    public Set<Book> getAll() {
        Set<Book> books = new HashSet<>();
        Set<BookDto> bookDtos = bookDtoRepository.selectAll();
        for (BookDto bookDto : bookDtos) {
            books.add(
                    new Book(
                            bookDto.getId(),
                            bookDto.getName(),
                            authorService.getByIds(bookDto.getAuthorIds()),
                            genreService.getByIds(bookDto.getGenreIds())
                    )
            );
        }
        return books;
    }
}
