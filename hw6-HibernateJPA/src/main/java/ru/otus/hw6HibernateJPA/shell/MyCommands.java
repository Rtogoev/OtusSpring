package ru.otus.hw6HibernateJPA.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.hw6HibernateJPA.utils.Utils;

import java.util.List;
import java.util.Set;

@ShellComponent
public class MyCommands {

    private final LibraryService libraryService;

    public MyCommands(LibraryService libraryService) {
        this.libraryService = libraryService;
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
    public List<LibraryRecord> getAll() {
        return libraryService.getAll();
    }

    @ShellMethod("get book by id")
    public LibraryRecord get(Long id) {
        return libraryService.get(id);
    }


    @ShellMethod("delete book by id")
    public void remove(Long id) {
        libraryService.remove(id);
    }
}
