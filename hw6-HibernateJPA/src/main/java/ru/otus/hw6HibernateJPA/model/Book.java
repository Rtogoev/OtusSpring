package ru.otus.hw6HibernateJPA.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "books")
public class Book {

    @Column(name = "name")
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(name = "book_author",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id"))
    private List<Author> authors;

    @ManyToMany
    @JoinTable(name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id", referencedColumnName = "id"))
    private List<Genre> genres;

    @ManyToMany
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
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(name, book.name) &&
                Objects.equals(authors, book.authors) &&
                Objects.equals(genres, book.genres) &&
                Objects.equals(commentaries, book.commentaries);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authors, genres, commentaries);
    }
}
