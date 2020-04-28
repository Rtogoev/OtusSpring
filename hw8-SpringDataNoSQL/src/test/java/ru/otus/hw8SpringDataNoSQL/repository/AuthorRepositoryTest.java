package ru.otus.hw8SpringDataNoSQL.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.hw8SpringDataNoSQL.model.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void insert() {
        String name = "insert";
        Long id = authorRepository.save(new Author(null, name)).getId();

        Author expectedAuthor = new Author(id, name);
        checkSelect(expectedAuthor);
    }

    @Test
    void update() {
        Long id = authorRepository.save(new Author(null, "update")).getId();
        Author expected = new Author(id, "update2");
        authorRepository.save(expected);
        checkSelect(expected);
    }

    @Test
    void delete() {
        Long id = authorRepository.save(new Author(null, "delete")).getId();

        authorRepository.deleteById(id);
        assertNull(authorRepository.findById(id).orElse(null));

    }

    private void checkSelect(Author expectedAuthor) {
        assertEquals(
                expectedAuthor,
                authorRepository.findById(expectedAuthor.getId())
                        .orElse(null)
        );

        assertEquals(
                expectedAuthor,
                authorRepository.findAuthorByName(expectedAuthor.getName())
        );

    }
}