package ru.otus.hw14SpringBatch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MongoBook {

    @Id
    private final String id;
    private final String name;
    private final String author;
    private final String genre;

    public MongoBook(String id, String name, String author, String genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }
}
