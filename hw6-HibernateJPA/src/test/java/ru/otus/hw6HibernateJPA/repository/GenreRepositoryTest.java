package ru.otus.hw6HibernateJPA.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw6HibernateJPA.model.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
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