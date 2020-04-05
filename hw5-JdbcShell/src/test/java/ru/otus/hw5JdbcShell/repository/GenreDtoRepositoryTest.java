package ru.otus.hw5JdbcShell.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw5JdbcShell.model.dto.GenreDto;

import java.util.List;

@JdbcTest
@Import(GenreDtoRepository.class)
class GenreDtoRepositoryTest {
    @Autowired
    private GenreDtoRepository genreDtoRepository;

    @Test
    void testAll() {
        String name = "test";
        Long id = genreDtoRepository.insert(name);

        GenreDto expectedbGenreDto = new GenreDto(id, name);

        Assertions.assertEquals(
                expectedbGenreDto,
                genreDtoRepository.select(expectedbGenreDto.getId())
        );

        expectedbGenreDto = new GenreDto(expectedbGenreDto.getId(), "test2");

        genreDtoRepository.update(expectedbGenreDto.getId(), expectedbGenreDto.getName());

        Assertions.assertEquals(
                expectedbGenreDto,
                genreDtoRepository.select(expectedbGenreDto.getId())
        );

        genreDtoRepository.delete(expectedbGenreDto.getId());

        Long id3 = genreDtoRepository.insert("test3");
        Long id4 = genreDtoRepository.insert("test4");
        Long id5 = genreDtoRepository.insert("test5");

        GenreDto expected3 = new GenreDto(id3, "test3");
        GenreDto expected4 = new GenreDto(id4, "test4");
        GenreDto expected5 = new GenreDto(id5, "test5");

        List<GenreDto> GenreDtos = genreDtoRepository.selectAll();

        Assertions.assertFalse(GenreDtos.contains(expectedbGenreDto));

        Assertions.assertTrue(GenreDtos.contains(expected3));
        Assertions.assertTrue(GenreDtos.contains(expected4));
        Assertions.assertTrue(GenreDtos.contains(expected5));
    }
}