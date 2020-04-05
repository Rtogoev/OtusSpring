package ru.otus.hw5JdbcShell.model;

import ru.otus.hw5JdbcShell.model.dto.AuthorDto;
import ru.otus.hw5JdbcShell.model.dto.GenreDto;

import java.util.Objects;
import java.util.Set;

public class Book {
    private final Long id;
    private final String name;
    private final Set<AuthorDto> authors;
    private final Set<GenreDto> genres;

    public Book(Long id, String name, Set<AuthorDto> authors, Set<GenreDto> genres) {
        this.id = id;
        this.name = name;
        this.authors = authors;
        this.genres = genres;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(name, book.name) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(genres, book.genres);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authors, genres);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<AuthorDto> getAuthors() {
        return authors;
    }

    public Set<GenreDto> getGenres() {
        return genres;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                '}';
    }
}
