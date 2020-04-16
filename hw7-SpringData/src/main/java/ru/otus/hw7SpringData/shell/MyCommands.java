package ru.otus.hw7SpringData.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw7SpringData.model.Book;
import ru.otus.hw7SpringData.service.LibraryService;
import ru.otus.hw7SpringData.utils.Utils;

import java.util.List;
import java.util.Set;

@ShellComponent
public class MyCommands {

    private final LibraryService libraryService;

    public MyCommands(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @ShellMethod("Example: add-commentary bookId text")
    public void addCommentary(Long bookId, String text) {
        libraryService.addCommentary(bookId, text);
    }

    @ShellMethod("Example: add name --authors author1,author2 --genres genre1,genre2")
    public Long add(String name, String authors, String genres) {
        Set<String> authorList = Utils.split(authors);
        Set<String> genreList = Utils.split(genres);
        return libraryService.add(name, authorList, genreList);
    }

    @ShellMethod("Example: update id name --authors author1,author2 --genres genre1,genre2")
    public void update(Long id, String name, String authors, String genres) {
        Set<String> authorList = Utils.split(authors);
        Set<String> genreList = Utils.split(genres);
        libraryService.update(id, name, authorList, genreList);
    }


    @ShellMethod("print all books in library")
    public List<Book> getAll() {
        return libraryService.getAll();
    }

    @ShellMethod("get book by id")
    public Book get(Long id) {
        return libraryService.get(id);
    }


    @ShellMethod("delete book by id")
    public void remove(Long id) {
        libraryService.remove(id);
    }
}
