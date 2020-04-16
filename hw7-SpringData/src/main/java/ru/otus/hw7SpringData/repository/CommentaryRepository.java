package ru.otus.hw7SpringData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw7SpringData.model.Commentary;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
    Commentary findCommentaryByText(String text);

    void update(Long id, String text);
}
