package ru.otus.hw5JdbcShell.model.view;

import ru.otus.hw5JdbcShell.model.dto.Author;
import ru.otus.hw5JdbcShell.model.dto.Genre;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

public class LibraryRecord {
    private final Long bookId;
    private final String bookName;
    private final List<Author> authors;
    private final List<Genre> genres;

    public LibraryRecord(Long bookId, String bookName, List<Author> authors, List<Genre> genres) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.authors = authors;
        this.genres = genres;
    }

    @Override
    public String toString() {
        return "LibraryRecord{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LibraryRecord that = (LibraryRecord) o;

        HashSet<Author> thisAuthorsSet = new HashSet<>(this.authors);
        thisAuthorsSet.removeAll(that.authors);

        HashSet<Author> thatAuthorsSet = new HashSet<>(that.authors);
        thatAuthorsSet.removeAll(this.authors);

        boolean isAuthorsEquals = false;
        if (thisAuthorsSet.size() == 0 && thatAuthorsSet.size() == 0) {
            isAuthorsEquals = true;
        }


        HashSet<Genre> thisGenresSet = new HashSet<>(this.genres);
        thisGenresSet.removeAll(that.genres);

        HashSet<Genre> thatGenresSet = new HashSet<>(that.genres);
        thatGenresSet.removeAll(this.genres);

        boolean isGenresEquals = false;
        if (thisGenresSet.size() == 0 && thatGenresSet.size() == 0) {
            isGenresEquals = true;
        }

        return Objects.equals(bookId, that.bookId) &&
                Objects.equals(bookName, that.bookName) &&
                isAuthorsEquals &&
                isGenresEquals;

    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, bookName, authors, genres);
    }
}

