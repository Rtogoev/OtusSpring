package ru.otus.hw6HibernateJPA.service;

import org.springframework.stereotype.Service;
import ru.otus.hw6HibernateJPA.model.Commentary;
import ru.otus.hw6HibernateJPA.repository.CommentaryRepository;

@Service
public class CommentaryService {
    private final CommentaryRepository commentaryRepository;

    public CommentaryService(CommentaryRepository commentaryRepository) {
        this.commentaryRepository = commentaryRepository;
    }

    public Commentary add(String text) {
        Commentary select = commentaryRepository.select(text);
        if (select == null) {
            return new Commentary(
                    commentaryRepository.insert(new Commentary(null, text)),
                    text
            );
        }
        return select;
    }
}
