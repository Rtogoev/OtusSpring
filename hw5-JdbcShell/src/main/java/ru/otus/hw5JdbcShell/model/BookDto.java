package ru.otus.hw5JdbcShell.model;

import java.util.Objects;

public class BookDto {
    private final Long id;
    private final String name;

    public BookDto(Long id, String name) {
        this.id = id;
        this.name = name;
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
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id) &&
                Objects.equals(name, bookDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
