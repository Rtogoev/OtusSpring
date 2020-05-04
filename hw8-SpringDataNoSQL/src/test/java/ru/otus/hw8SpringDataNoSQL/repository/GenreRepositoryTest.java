package ru.otus.hw8SpringDataNoSQL.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import ru.otus.hw8SpringDataNoSQL.model.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataMongoTest
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
        String id = genreRepository.save(new Genre(null, "update")).getId();
        Genre update = new Genre(id, "update2");
        genreRepository.save(update);
        checkSelect(update);
    }

    @Test
    void delete() {
        String id = genreRepository.save(new Genre(null, "delete")).getId();

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
                genreRepository.findByName(expectedGenre.getName()).get()
        );

    }
}