package ru.otus.hw10jQuery.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Document
public class Book {

    @Id
    private final String id;
    private final String name;
    @DBRef
    private final List<Author> authors;
    @DBRef
    private final List<Genre> genres;
    private final List<String> commentaries;

    public Book(String name, String id, List<Author> authors, List<Genre> genres, List<String> commentaries) {
        this.name = name;
        this.id = id;
        this.authors = authors;
        this.genres = genres;
        this.commentaries = commentaries;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public List<String> getCommentaries() {
        return commentaries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;

        boolean areAuthorsEquals = false;
        if (this.authors != null && that.authors != null) {
            HashSet<Author> thisAuthorsSet = new HashSet<>(this.authors);
            thisAuthorsSet.removeAll(that.authors);

            HashSet<Author> thatAuthorsSet = new HashSet<>(that.authors);
            thatAuthorsSet.removeAll(this.authors);

            if (thisAuthorsSet.size() == 0 && thatAuthorsSet.size() == 0) {
                areAuthorsEquals = true;
            }
        }
        if (this.authors == null && that.authors == null) {
            areAuthorsEquals = true;
        }

        boolean areGenresEquals = false;
        if (this.genres != null && that.genres != null) {

            HashSet<Genre> thisGenresSet = new HashSet<>(this.genres);
            thisGenresSet.removeAll(that.genres);

            HashSet<Genre> thatGenresSet = new HashSet<>(that.genres);
            thatGenresSet.removeAll(this.genres);

            if (thisGenresSet.size() == 0 && thatGenresSet.size() == 0) {
                areGenresEquals = true;
            }
        }
        if (this.genres == null && that.genres == null) {
            areGenresEquals = true;
        }

        boolean areCommentariesEquals = false;
        if (this.commentaries != null && that.commentaries != null) {
            HashSet<String> thisCommentariesSet = new HashSet<>(this.commentaries);
            thisCommentariesSet.removeAll(that.commentaries);

            HashSet<String> thatCommentariesSet = new HashSet<>(that.commentaries);
            thatCommentariesSet.removeAll(this.commentaries);

            if (thisCommentariesSet.size() == 0 && thatCommentariesSet.size() == 0) {
                areCommentariesEquals = true;
            }
        }
        if (this.commentaries == null && that.commentaries == null) {
            areCommentariesEquals = true;
        }

        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                areAuthorsEquals &&
                areGenresEquals &&
                areCommentariesEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authors, genres, commentaries);
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                ", commentaries=" + commentaries +
                '}';
    }
}
