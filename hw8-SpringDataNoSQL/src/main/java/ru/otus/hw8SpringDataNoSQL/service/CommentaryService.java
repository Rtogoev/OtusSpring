package ru.otus.hw8SpringDataNoSQL.service;

import org.springframework.stereotype.Service;
import ru.otus.hw8SpringDataNoSQL.model.Commentary;
import ru.otus.hw8SpringDataNoSQL.repository.CommentaryRepository;

@Service
public class CommentaryService {
    private final CommentaryRepository commentaryRepository;

    public CommentaryService(CommentaryRepository commentaryRepository) {
        this.commentaryRepository = commentaryRepository;
    }

    public Commentary add(String text) {
        Commentary select = commentaryRepository.findCommentaryByText(text);
        if (select == null) {
            return commentaryRepository.save(new Commentary(null, text));
        }
        return select;
    }
}
