package ru.otus.hw5JdbcShell.model.dto;

import java.util.Objects;

public class GenreDto {
    private final Long id;
    private final String name;

    public GenreDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "GenreDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GenreDto genreDto = (GenreDto) o;
        return Objects.equals(id, genreDto.id) &&
                Objects.equals(name, genreDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
