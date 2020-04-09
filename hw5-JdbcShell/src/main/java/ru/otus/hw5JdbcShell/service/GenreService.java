package ru.otus.hw5JdbcShell.service;

import org.springframework.stereotype.Service;
import ru.otus.hw5JdbcShell.model.dto.Genre;
import ru.otus.hw5JdbcShell.repository.GenreRepository;

import java.util.HashSet;
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

    public Long add(String name) {
        Genre select = genreRepository.select(name);
        if (select == null) {
            return genreRepository.insert(name);
        }
        return select.getId();
    }

    public Set<Long> add(Set<String> genres) {
        Set<Long> genreIds = new HashSet<>();
        for (String genre : genres) {
            Long genreId = add(genre);
            genreIds.add(genreId);
        }
        return genreIds;
    }

    public List<Genre> getByIds(Set<Long> genreIds) {
        return genreRepository.select(genreIds);
    }
}
