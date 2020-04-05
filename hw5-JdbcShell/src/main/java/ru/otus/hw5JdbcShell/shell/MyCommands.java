package ru.otus.hw5JdbcShell.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw5JdbcShell.model.Book;
import ru.otus.hw5JdbcShell.service.BookService;
import ru.otus.hw5JdbcShell.utils.Utils;

import java.util.Set;

@ShellComponent
public class MyCommands {

    private final BookService bookService;

    public MyCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod("Example: add name --authors author1,author2 --genres genre1,genre2")
    public Long add(String name, String authors, String genres) {
        Set<String> authorList = Utils.convert(authors);
        Set<String> genreList = Utils.convert(genres);
        return bookService.add(name, authorList, genreList);
    }

    @ShellMethod("Example: update  name --authors author1,author2 --genres genre1,genre2")
    public void update(Long id, String name, String authors, String genres) {
        Set<String> authorList = Utils.convert(authors);
        Set<String> genreList = Utils.convert(genres);
        bookService.update(id, name, authorList, genreList);
    }


    @ShellMethod("print all books in library")
    public Set<Book> getAll() {
        return bookService.getAll();
    }

    @ShellMethod("get book by id")
    public Book get(Long id) {
        return bookService.get(id);
    }


    @ShellMethod("delete book by id")
    public void remove(Long id) {
        bookService.remove(id);
    }
}
