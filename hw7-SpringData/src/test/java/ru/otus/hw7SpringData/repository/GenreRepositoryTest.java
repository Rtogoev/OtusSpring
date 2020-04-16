package ru.otus.hw7SpringData.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw7SpringData.model.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(GenreRepository.class)
class GenreRepositoryTest {
    @Autowired
    private GenreRepository genreRepository;

    @Test
    void insert() {
        String name = "insert";

        Genre expectedGenre = genreRepository.save(new Genre(null, name));
        checkSelect(expectedGenre);
    }

    @Test
    void update() {
        Long id = genreRepository.save(new Genre(null, "update")).getId();
        genreRepository.update(id, "update2");
        checkSelect(new Genre(id, "update2"));
    }

    @Test
    void delete() {
        Long id = genreRepository.save(new Genre(null, "delete")).getId();

        genreRepository.deleteById(id);
        assertNull(genreRepository.findById(id).orElse(null));

    }

    private void checkSelect(Genre expectedGenre) {
        assertEquals(
                expectedGenre,
                genreRepository.findById(expectedGenre.getId()).orElse(null)
        );

        assertEquals(
                expectedGenre,
                genreRepository.findGenreByName(expectedGenre.getName())
        );

    }
}