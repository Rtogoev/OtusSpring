package ru.otus.hw7SpringData.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw7SpringData.model.Commentary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@Import(CommentaryRepository.class)
class CommentaryRepositoryTest {
    @Autowired
    private CommentaryRepository commentaryRepository;

    @Test
    void insert() {
        String name = "insert";
        Long id = commentaryRepository.insert(new Commentary(null, name));

        Commentary expectedCommentary = new Commentary(id, name);
        checkSelect(expectedCommentary);
    }

    @Test
    void update() {
        Long id = commentaryRepository.insert(new Commentary(null, "update"));
        commentaryRepository.update(id, "update2");
        checkSelect(new Commentary(id, "update2"));
    }

    @Test
    void delete() {
        Long id = commentaryRepository.insert(new Commentary(null, "delete"));

        commentaryRepository.delete(id);
        assertNull(commentaryRepository.select(id));

    }

    private void checkSelect(Commentary expectedCommentary) {
        assertEquals(
                expectedCommentary,
                commentaryRepository.select(expectedCommentary.getId())
        );

        assertEquals(
                expectedCommentary,
                commentaryRepository.select(expectedCommentary.getText())
        );

    }
}