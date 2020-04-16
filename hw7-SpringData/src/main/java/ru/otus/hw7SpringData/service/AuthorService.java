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

    public Author add(String name) {
        Author select = authorRepository.findAuthorByName(name);
        if (select == null) {
            return authorRepository.save(new Author(null, name));
        }
        return select;
    }

    public List<Author> add(Set<String> authorNames) {
        List<Author> authorList = new ArrayList<>();
        for (String authorName : authorNames) {
            authorList.add(
                    add(authorName)
            );
        }
        return authorList;
    }
}
