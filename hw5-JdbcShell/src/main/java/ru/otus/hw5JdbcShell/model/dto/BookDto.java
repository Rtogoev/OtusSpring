package ru.otus.hw5JdbcShell.model.dto;

import java.util.List;
import java.util.Objects;

public class BookDto {
    private final Long id;
    private final String name;
    private final List<Long> authorIds;
    private final List<Long> genreIds;

    public BookDto(Long id, String name, List<Long> authorIds, List<Long> genreIds) {
        this.id = id;
        this.name = name;
        this.authorIds = authorIds;
        this.genreIds = genreIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id) &&
                Objects.equals(name, bookDto.name) &&
                Objects.equals(authorIds, bookDto.authorIds) &&
                Objects.equals(genreIds, bookDto.genreIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, authorIds, genreIds);
    }

    public List<Long> getAuthorIds() {
        return authorIds;
    }

    public List<Long> getGenreIds() {
        return genreIds;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
