package ru.otus.hw7SpringData.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw7SpringData.model.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(AuthorRepository.class)
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void insert() {
        String name = "insert";
        Long id = authorRepository.insert(new Author(null, name));

        Author expectedAuthor = new Author(id, name);
        checkSelect(expectedAuthor);
    }

    @Test
    void update() {
        Long id = authorRepository.insert(new Author(null, "update"));
        authorRepository.update(id, "update2");
        checkSelect(new Author(id, "update2"));
    }

    @Test
    void delete() {
        Long id = authorRepository.insert(new Author(null, "delete"));

        authorRepository.delete(id);
        assertNull(authorRepository.select(id));

    }

    private void checkSelect(Author expectedAuthor) {
        assertEquals(
                expectedAuthor,
                authorRepository.select(expectedAuthor.getId())
        );

        assertEquals(
                expectedAuthor,
                authorRepository.select(expectedAuthor.getName())
        );

    }
}