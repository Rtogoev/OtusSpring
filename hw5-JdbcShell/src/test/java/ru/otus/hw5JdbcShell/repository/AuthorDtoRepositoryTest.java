package ru.otus.hw5JdbcShell.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw5JdbcShell.model.dto.AuthorDto;

import java.util.List;

@JdbcTest
@Import(AuthorDtoRepository.class)
class AuthorDtoRepositoryTest {
    @Autowired
    private AuthorDtoRepository authorDtoRepository;

    private void checkSelect(AuthorDto expectedAuthorDto) {

        Assertions.assertEquals(
                expectedAuthorDto,
                authorDtoRepository.select(expectedAuthorDto.getId())
        );

        Assertions.assertEquals(
                expectedAuthorDto,
                authorDtoRepository.select(expectedAuthorDto.getName())
        );

    }
    @Test
    void testAll() {
        String name = "test";
        Long id = authorDtoRepository.insert(name);

        AuthorDto expectedbAuthorDto = new AuthorDto(id, name);

        checkSelect(expectedbAuthorDto);

        expectedbAuthorDto = new AuthorDto(expectedbAuthorDto.getId(), "test2");

        authorDtoRepository.update(expectedbAuthorDto.getId(), expectedbAuthorDto.getName());

        checkSelect(expectedbAuthorDto);

        authorDtoRepository.delete(expectedbAuthorDto.getId());

        Long id3 = authorDtoRepository.insert("test3");
        Long id4 = authorDtoRepository.insert("test4");
        Long id5 = authorDtoRepository.insert("test5");

        AuthorDto expected3 = new AuthorDto(id3, "test3");
        AuthorDto expected4 = new AuthorDto(id4, "test4");
        AuthorDto expected5 = new AuthorDto(id5, "test5");

        List<AuthorDto> AuthorDtos = authorDtoRepository.selectAll();

        Assertions.assertFalse(AuthorDtos.contains(expectedbAuthorDto));

        Assertions.assertTrue(AuthorDtos.contains(expected3));
        Assertions.assertTrue(AuthorDtos.contains(expected4));
        Assertions.assertTrue(AuthorDtos.contains(expected5));
    }
}