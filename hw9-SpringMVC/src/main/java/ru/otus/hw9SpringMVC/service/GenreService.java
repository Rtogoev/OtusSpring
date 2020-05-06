package ru.otus.hw9SpringMVC.service;

import org.springframework.stereotype.Service;
import ru.otus.hw9SpringMVC.model.Genre;
import ru.otus.hw9SpringMVC.repository.GenreRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(
            GenreRepository genreRepository
    ) {
        this.genreRepository = genreRepository;
    }

    public Genre add(String name) {
        return genreRepository.findByName(name)
                .orElseGet(
                        () -> genreRepository.save(new Genre(null, name))
                );
    }

    public List<Genre> add(Set<String> genreNames) {
        return genreNames.stream()
                .map(this::add)
                .collect(Collectors.toList());
    }
}
