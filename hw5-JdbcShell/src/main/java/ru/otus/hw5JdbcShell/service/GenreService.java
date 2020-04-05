package ru.otus.hw5JdbcShell.service;

import org.springframework.stereotype.Service;
import ru.otus.hw5JdbcShell.model.dto.GenreDto;
import ru.otus.hw5JdbcShell.repository.GenreDtoRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class GenreService {
    private final GenreDtoRepository genreDtoRepository;

    public GenreService(
            GenreDtoRepository genreDtoRepository
    ) {
        this.genreDtoRepository = genreDtoRepository;
    }

    public Long add(String name) {
        GenreDto select = genreDtoRepository.select(name);
        if (select == null) {
            return genreDtoRepository.insert(name);
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

    public GenreDto get(String name) {
        return genreDtoRepository.select(name);
    }

    public GenreDto get(Long id) {
        return genreDtoRepository.select(id);
    }

    public Set<GenreDto> getByNames(Set<String> genres) {
        Set<GenreDto> genreDtos = new HashSet<>();
        for (String genre : genres) {
            genreDtos.add(get(genre));
        }
        return genreDtos;
    }

    public Set<GenreDto> getByIds(Set<Long> genreIds) {
        Set<GenreDto> genreDtos = new HashSet<>();
        for (Long genreId : genreIds) {
            genreDtos.add(get(genreId));
        }
        return genreDtos;
    }
}
