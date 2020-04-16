package ru.otus.hw7SpringData.service;

import org.springframework.stereotype.Service;
import ru.otus.hw7SpringData.model.Author;
import ru.otus.hw7SpringData.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Long add(String name) {
        Author select = authorRepository.select(name);
        if (select == null) {
            return authorRepository.insert(new Author(null, name));
        }
        return select.getId();
    }

    public List<Author> add(Set<String> authorNames) {
        List<Author> authorList = new ArrayList<>();
        for (String authorName : authorNames) {
            Long authorId = add(authorName);
            authorList.add(new Author(authorId, authorName));
        }
        return authorList;
    }
}
