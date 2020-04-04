package ru.otus.hw5JdbcShell.model;

import java.util.Objects;

public class AuthorDto {
    private final Long id;
    private final String name;

    public AuthorDto(Long id, String name) {
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
        AuthorDto authorDto = (AuthorDto) o;
        return Objects.equals(id, authorDto.id) &&
                Objects.equals(name, authorDto.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
