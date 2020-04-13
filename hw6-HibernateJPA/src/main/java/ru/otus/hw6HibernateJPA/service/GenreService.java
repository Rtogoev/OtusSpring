package ru.otus.hw6HibernateJPA.service;

import org.springframework.stereotype.Service;
import ru.otus.hw6HibernateJPA.model.Genre;
import ru.otus.hw6HibernateJPA.repository.GenreRepository;

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
