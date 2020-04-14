package ru.otus.hw6HibernateJPA.service;

import org.springframework.stereotype.Service;
import ru.otus.hw6HibernateJPA.model.Commentary;
import ru.otus.hw6HibernateJPA.repository.CommentaryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    public List<Commentary> add(Set<String> commentaryTexts) {
        List<Commentary> commentaryList = new ArrayList<>();
        for (String commentaryName : commentaryTexts) {
            commentaryList.add(add(commentaryName));
        }
        return commentaryList;
    }
}
