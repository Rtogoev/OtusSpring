package ru.otus.hw6HibernateJPA.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw6HibernateJPA.model.Author;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
@JdbcTest
@Import(AuthorRepository.class)
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void insert() {
        String name = "test";
        Long id = authorRepository.insert(name);

        Author expectedAuthor = new Author(id, name);
        checkSelect(expectedAuthor);
    }

    @Test
    void update() {
        Long id = authorRepository.insert("test");
        authorRepository.update(id, "test2");
        checkSelect(new Author(id, "test2"));
    }

    @Test
    void delete() {
        Long id = authorRepository.insert("test");

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