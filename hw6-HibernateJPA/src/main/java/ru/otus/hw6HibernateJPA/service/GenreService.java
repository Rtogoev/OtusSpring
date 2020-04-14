package ru.otus.hw6HibernateJPA.service;

import org.springframework.stereotype.Service;
import ru.otus.hw6HibernateJPA.model.Genre;
import ru.otus.hw6HibernateJPA.repository.GenreRepository;

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

    public Long add(String name) {
        Genre select = genreRepository.select(name);
        if (select == null) {
            return genreRepository.insert(new Genre(null, name));
        }
        return select.getId();
    }

    public List<Genre> add(Set<String> genreNames) {
        List<Genre> genreList = new ArrayList();
        for (String genreName : genreNames) {
            Long genreId = add(genreName);
            genreList.add(new Genre(genreId, genreName));
        }
        return genreList;
    }

    public List<Genre> getByIds(Set<Long> genreIds) {
        return genreRepository.select(genreIds);
    }
}
