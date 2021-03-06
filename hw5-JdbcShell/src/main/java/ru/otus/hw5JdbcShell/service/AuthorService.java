package ru.otus.hw5JdbcShell.service;

import org.springframework.stereotype.Service;
import ru.otus.hw5JdbcShell.model.dto.Author;
import ru.otus.hw5JdbcShell.repository.AuthorRepository;

import java.util.HashSet;
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
            return authorRepository.insert(name);
        }
        return select.getId();
    }

    public Set<Long> add(Set<String> authors) {
        Set<Long> authorsIds = new HashSet<>();
        for (String author : authors) {
            Long authorId = add(author);
            authorsIds.add(authorId);
        }
        return authorsIds;
    }

    public List<Author> getByIds(Set<Long> authorIds) {
        return authorRepository.select(authorIds);
    }
}
