package ru.otus.hw8SpringDataNoSQL.service;

import org.springframework.stereotype.Service;
import ru.otus.hw8SpringDataNoSQL.model.Genre;
import ru.otus.hw8SpringDataNoSQL.repository.GenreRepository;

import java.util.ArrayList;
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
        List<Genre> genreList = new ArrayList<>();
        genreRepository.saveAll(
                genreNames.stream()
                .map(name -> new Genre(null, name))
                .collect(Collectors.toList())
        )
                .forEach(genreList::add);
        return genreList;
    }
}
