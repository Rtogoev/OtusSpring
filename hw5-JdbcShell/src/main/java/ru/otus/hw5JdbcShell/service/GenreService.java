package ru.otus.hw5JdbcShell.service;

import org.springframework.stereotype.Service;
import ru.otus.hw5JdbcShell.model.dto.GenreDto;
import ru.otus.hw5JdbcShell.repository.GenreDtoRepository;

import java.util.ArrayList;
import java.util.List;

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

    public List<Long> add(List<String> genres) {
        List<Long> genreIds = new ArrayList<>();
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

    public List<GenreDto> getByNames(List<String> genres) {
        List<GenreDto> genreDtos = new ArrayList<>();
        for (String genre : genres) {
            genreDtos.add(get(genre));
        }
        return genreDtos;
    }

    public List<GenreDto> getByIds(List<Long> genreIds) {
        List<GenreDto> genreDtos = new ArrayList<>();
        for (Long genreId : genreIds) {
            genreDtos.add(get(genreId));
        }
        return genreDtos;
    }
}
