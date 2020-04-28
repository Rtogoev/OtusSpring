package ru.otus.hw8SpringDataNoSQL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw8SpringDataNoSQL.model.Commentary;

public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
    Commentary findCommentaryByText(String text);
}
