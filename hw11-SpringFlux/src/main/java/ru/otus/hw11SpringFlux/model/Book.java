package ru.otus.hw11SpringFlux.model;

import org.springframework.data.annotation.Id;

public class Book {
    @Id
    private String id;
    private String name;
    private String authorsString;
    private String genresString;

    public Book(
            String id,
            String name,
            String authorsString,
            String genresString
    ) {
        this.id = id;
        this.name = name;
        this.authorsString = authorsString;
        this.genresString = genresString;
    }

    public Book() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthorsString() {
        return authorsString;
    }

    public void setAuthorsString(String authorsString) {
        this.authorsString = authorsString;
    }

    public String getGenresString() {
        return genresString;
    }

    public void setGenresString(String genresString) {
        this.genresString = genresString;
    }
}
