package ru.otus.hw8SpringDataNoSQL.service;

import org.springframework.stereotype.Service;
import ru.otus.hw8SpringDataNoSQL.model.Genre;
import ru.otus.hw8SpringDataNoSQL.repository.GenreRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GenreService {
    private final GenreRepository genreRepository;

    public GenreService(
            GenreRepository genreRepository
    ) {
        this.genreRepository = genreRepository;
    }

    public Genre add(String name) {
        Genre select = genreRepository.findGenreByName(name);
        if (select == null) {
            return genreRepository.save(new Genre(null, name));
        }
        return select;
    }

    public List<Genre> add(Set<String> genreNames) {
        List<Genre> genreList = new ArrayList<>();
        for (String genreName : genreNames) {
            genreList.add(
                    add(genreName)
            );
        }
        return genreList;
    }
}
