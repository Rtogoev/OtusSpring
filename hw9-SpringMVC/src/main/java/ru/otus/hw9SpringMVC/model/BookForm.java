package ru.otus.hw9SpringMVC.model;

public class BookForm {
    private final String id;
    private final String name;
    private final String authorsString;
    private final String genresString;
    private final String commentariesString;

    public BookForm(
            String id,
            String name,
            String authorsString,
            String genresString,
            String commentariesString
    ) {
        this.id = id;
        this.name = name;
        this.authorsString = authorsString;
        this.genresString = genresString;
        this.commentariesString = commentariesString;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthorsString() {
        return authorsString;
    }

    public String getGenresString() {
        return genresString;
    }

    public String getCommentariesString() {
        return commentariesString;
    }
}
