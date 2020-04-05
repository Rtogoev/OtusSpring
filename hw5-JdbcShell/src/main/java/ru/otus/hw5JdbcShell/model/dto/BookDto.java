package ru.otus.hw5JdbcShell.model.dto;

import java.util.Objects;
import java.util.Set;

public class BookDto {
    private final Long id;
    private final String name;
    private final Set<Long> authorIds;
    private final Set<Long> genreIds;

    public BookDto(Long id, String name, Set<Long> authorIds, Set<Long> genreIds) {
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

    public Set<Long> getAuthorIds() {
        return authorIds;
    }

    public Set<Long> getGenreIds() {
        return genreIds;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorIds=" + authorIds +
                ", genreIds=" + genreIds +
                '}';
    }
}
