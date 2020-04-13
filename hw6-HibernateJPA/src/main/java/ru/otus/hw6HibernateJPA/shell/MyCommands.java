package ru.otus.hw6HibernateJPA.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw6HibernateJPA.model.Book;
import ru.otus.hw6HibernateJPA.service.BookService;
import ru.otus.hw6HibernateJPA.utils.Utils;

import java.util.List;
import java.util.Set;

@ShellComponent
public class MyCommands {

    private final BookService bookService;

    public MyCommands(BookService bookService) {
        this.bookService = bookService;
    }

    @ShellMethod("Example: add name --authors author1,author2 --genres genre1,genre2")
    public Long add(String name, String authors, String genres) {
        Set<String> authorList = Utils.split(authors);
        Set<String> genreList = Utils.split(genres);
//        return bookService.add(name, authorList, genreList);
        return null;
    }

    @ShellMethod("Example: update id name --authors author1,author2 --genres genre1,genre2")
    public void update(Long id, String name, String authors, String genres) {
        Set<String> authorList = Utils.split(authors);
        Set<String> genreList = Utils.split(genres);
//        bookService.update(id, name, authorList, genreList);
    }


    @ShellMethod("print all books in library")
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @ShellMethod("get book by id")
    public Book get(Long id) {
        return bookService.get(id);
    }


//    @ShellMethod("delete book by id")
//    public void remove(Long id) {
//        bookService.remove(id);
//    }
}
