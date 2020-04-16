package ru.otus.hw7SpringData.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

@Entity
public class Book {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private List<Author> authors;

    @Fetch(FetchMode.SUBSELECT)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private List<Genre> genres;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_commentary",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "commentary_id", referencedColumnName = "id"))
    private List<Commentary> commentaries;

    public Book() {
    }

    public Book(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book(String name, Long id, List<Author> authors, List<Genre> genres, List<Commentary> commentaries) {
        this.name = name;
        this.id = id;
        this.authors = authors;
        this.genres = genres;
        this.commentaries = commentaries;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authors=" + authors +
                ", genres=" + genres +
                ", commentaries=" + commentaries +
                '}';
    }

    public Long getId() {
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

    public List<Commentary> getCommentaries() {
        return commentaries;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;

        boolean isAuthorsEquals = false;
        if (this.authors != null && that.authors != null) {
            HashSet<Author> thisAuthorsSet = new HashSet<>(this.authors);
            thisAuthorsSet.removeAll(that.authors);

            HashSet<Author> thatAuthorsSet = new HashSet<>(that.authors);
            thatAuthorsSet.removeAll(this.authors);

            if (thisAuthorsSet.size() == 0 && thatAuthorsSet.size() == 0) {
                isAuthorsEquals = true;
            }
        }
        if (this.authors == null && that.authors == null) {
            isAuthorsEquals = true;
        }

        boolean isGenresEquals = false;
        if (this.genres != null && that.genres != null) {

            HashSet<Genre> thisGenresSet = new HashSet<>(this.genres);
            thisGenresSet.removeAll(that.genres);

            HashSet<Genre> thatGenresSet = new HashSet<>(that.genres);
            thatGenresSet.removeAll(this.genres);

            if (thisGenresSet.size() == 0 && thatGenresSet.size() == 0) {
                isGenresEquals = true;
            }
        }
        if (this.genres == null && that.genres == null) {
            isGenresEquals = true;
        }

        boolean isCommentariesEquals = false;
        if (this.commentaries != null && that.commentaries != null) {
            HashSet<Commentary> thisCommentariesSet = new HashSet<>(this.commentaries);
            thisCommentariesSet.removeAll(that.commentaries);

            HashSet<Commentary> thatCommentariesSet = new HashSet<>(that.commentaries);
            thatCommentariesSet.removeAll(this.commentaries);

            if (thisCommentariesSet.size() == 0 && thatCommentariesSet.size() == 0) {
                isCommentariesEquals = true;
            }
        }
        if (this.commentaries == null && that.commentaries == null) {
            isCommentariesEquals = true;
        }

        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                isAuthorsEquals &&
                isGenresEquals &&
                isCommentariesEquals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authors, genres, commentaries);
    }
}
