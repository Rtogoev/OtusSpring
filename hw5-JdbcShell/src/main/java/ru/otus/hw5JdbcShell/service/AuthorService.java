package ru.otus.hw5JdbcShell.service;

import org.springframework.stereotype.Service;
import ru.otus.hw5JdbcShell.model.dto.AuthorDto;
import ru.otus.hw5JdbcShell.repository.AuthorDtoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    private final AuthorDtoRepository authorDtoRepository;

    public AuthorService(AuthorDtoRepository authorDtoRepository) {
        this.authorDtoRepository = authorDtoRepository;
    }

    public Long add(String name) {
        AuthorDto select = authorDtoRepository.select(name);
        if (select == null) {
            return authorDtoRepository.insert(name);
        }
        return select.getId();
    }

    public List<Long> add(List<String> authors) {
        List<Long> authorsIds = new ArrayList<>();
        for (String author : authors) {
            Long authorId = add(author);
            authorsIds.add(authorId);
        }
        return authorsIds;
    }

    public AuthorDto get(String name) {
        return authorDtoRepository.select(name);
    }


    public AuthorDto get(Long id) {
        return authorDtoRepository.select(id);
    }

    public List<AuthorDto> getByNames(List<String> authors) {
        List<AuthorDto> authorDtos = new ArrayList<>();
        for (String author : authors) {
            authorDtos.add(get(author));
        }
        return authorDtos;
    }


    public List<AuthorDto> getByIds(List<Long> authors) {
        List<AuthorDto> authorDtos = new ArrayList<>();
        for (Long authorId : authors) {
            authorDtos.add(get(authorId));
        }
        return authorDtos;
    }
}
