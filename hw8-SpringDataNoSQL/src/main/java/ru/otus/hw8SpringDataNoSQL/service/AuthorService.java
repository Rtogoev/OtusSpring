package ru.otus.hw8SpringDataNoSQL.service;

import org.springframework.stereotype.Service;
import ru.otus.hw8SpringDataNoSQL.model.Author;
import ru.otus.hw8SpringDataNoSQL.repository.AuthorRepository;

import java.util.ArrayList;
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
        List<Author> authors = new ArrayList<>();
        authorRepository.saveAll(
                authorNames.stream()
                        .map(name -> new Author(null, name))
                        .collect(Collectors.toList())
        )
                .forEach(authors::add);
        return authors;
    }
}
