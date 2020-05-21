package ru.otus.hw10jQuery.model;

public class BookForm {
    private String id;
    private String name;
    private String authorsString;
    private String genresString;
    private String commentariesString;

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

    public BookForm() {
    }

    public String getCommentariesString() {
        return commentariesString;
    }

    public void setCommentariesString(String commentariesString) {
        this.commentariesString = commentariesString;
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
