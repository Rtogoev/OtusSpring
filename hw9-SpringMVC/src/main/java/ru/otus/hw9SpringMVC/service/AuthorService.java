package ru.otus.hw9SpringMVC.service;

import org.springframework.stereotype.Service;
import ru.otus.hw9SpringMVC.model.Author;
import ru.otus.hw9SpringMVC.repository.AuthorRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author add(String name) {
        return authorRepository.findByName(name)
                .orElseGet(
                        () -> authorRepository.save(new Author(null, name))
                );
    }

    public List<Author> add(Set<String> authorNames) {
        return authorNames.stream()
                .map(this::add)
                .collect(Collectors.toList());
    }
}
