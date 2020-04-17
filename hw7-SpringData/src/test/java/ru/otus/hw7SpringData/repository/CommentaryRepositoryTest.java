package ru.otus.hw7SpringData.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import ru.otus.hw7SpringData.model.Commentary;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
class CommentaryRepositoryTest {
    @Autowired
    private CommentaryRepository commentaryRepository;

    @Test
    void insert() {
        String name = "insert";
        Commentary expectedCommentary = commentaryRepository.save(new Commentary(null, name));
        checkSelect(expectedCommentary);
    }

    @Test
    void update() {
        Long id = commentaryRepository.save(new Commentary(null, "update")).getId();
        Commentary update = new Commentary(id, "update2");
        commentaryRepository.save(update);
        checkSelect(update);
    }

    @Test
    void delete() {
        Long id = commentaryRepository.save(new Commentary(null, "delete")).getId();

        commentaryRepository.deleteById(id);
        assertNull(commentaryRepository.findById(id).orElse(null));

    }

    private void checkSelect(Commentary expectedCommentary) {
        assertEquals(
                expectedCommentary,
                commentaryRepository.findById(expectedCommentary.getId())
                        .orElse(null)
        );

        assertEquals(
                expectedCommentary,
                commentaryRepository.findCommentaryByText(expectedCommentary.getText())
        );

    }
}