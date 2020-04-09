package ru.otus.hw5JdbcShell.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw5JdbcShell.model.dto.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@JdbcTest
@Import(GenreRepository.class)
class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;

    @Test
    void insert() {
        String name = "test";
        Long id = genreRepository.insert(name);

        Genre expectedGenre = new Genre(id, name);
        checkSelect(expectedGenre);
    }

    @Test
    void update() {
        Long id = genreRepository.insert("test");
        genreRepository.update(id, "test2");
        checkSelect(new Genre(id, "test2"));
    }

    @Test
    void delete() {
        Long id = genreRepository.insert("test");

        genreRepository.delete(id);
        assertNull(genreRepository.select(id));

    }

    private void checkSelect(Genre expectedGenre) {

        assertEquals(
                expectedGenre,
                genreRepository.select(expectedGenre.getId())
        );

        assertEquals(
                expectedGenre,
                genreRepository.select(expectedGenre.getName())
        );

    }
}