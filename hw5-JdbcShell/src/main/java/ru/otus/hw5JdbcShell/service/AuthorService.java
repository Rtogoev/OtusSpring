package ru.otus.hw5JdbcShell.service;

import org.springframework.stereotype.Service;
import ru.otus.hw5JdbcShell.model.dto.AuthorDto;
import ru.otus.hw5JdbcShell.repository.AuthorDtoRepository;

import java.util.HashSet;
import java.util.Set;

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

    public Set<Long> add(Set<String> authors) {
        Set<Long> authorsIds = new HashSet<>();
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

    public Set<AuthorDto> getByNames(Set<String> authors) {
        Set<AuthorDto> authorDtos = new HashSet<>();
        for (String author : authors) {
            authorDtos.add(get(author));
        }
        return authorDtos;
    }


    public Set<AuthorDto> getByIds(Set<Long> authors) {
        Set<AuthorDto> authorDtos = new HashSet<>();
        for (Long authorId : authors) {
            authorDtos.add(get(authorId));
        }
        return authorDtos;
    }
}
