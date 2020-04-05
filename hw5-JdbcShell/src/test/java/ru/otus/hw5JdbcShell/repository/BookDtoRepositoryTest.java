package ru.otus.hw5JdbcShell.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw5JdbcShell.model.dto.BookDto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@JdbcTest
@Import(BookDtoRepository.class)
class BookDtoRepositoryTest {
    @Autowired
    private BookDtoRepository bookDtoRepository;

    @Test
    void testAll() {
        String name = "test";

        List<Long> authorIds = new ArrayList<>();
        authorIds.add(1L);
        authorIds.add(2L);
        authorIds.add(3L);


        List<Long> genreIds = new ArrayList<>();
        genreIds.add(1L);
        genreIds.add(2L);
        genreIds.add(3L);

        Long id = bookDtoRepository.insert(name, authorIds, genreIds);

        BookDto expectedbBookDto = new BookDto(id, name, authorIds, genreIds);

        Assertions.assertEquals(
                expectedbBookDto,
                bookDtoRepository.select(expectedbBookDto.getId())
        );

        authorIds.remove(0);
        genreIds.remove(0);

        expectedbBookDto = new BookDto(expectedbBookDto.getId(), "test2", authorIds, genreIds);

        bookDtoRepository.update(
                expectedbBookDto.getId(),
                expectedbBookDto.getName(),
                expectedbBookDto.getAuthorIds(),
                expectedbBookDto.getGenreIds()
        );

        Assertions.assertEquals(
                expectedbBookDto,
                bookDtoRepository.select(expectedbBookDto.getId())
        );

        bookDtoRepository.delete(expectedbBookDto.getId());

        Long id3 = bookDtoRepository.insert("test3", Collections.singletonList(3L), Collections.singletonList(3L));
        Long id4 = bookDtoRepository.insert("test4", Collections.singletonList(4L), Collections.singletonList(4L));
        Long id5 = bookDtoRepository.insert("test5", Collections.singletonList(5L), Collections.singletonList(5L));

        BookDto expected3 = new BookDto(id3, "test3", Collections.singletonList(3L), Collections.singletonList(3L));
        BookDto expected4 = new BookDto(id4, "test4", Collections.singletonList(4L), Collections.singletonList(4L));
        BookDto expected5 = new BookDto(id5, "test5", Collections.singletonList(5L), Collections.singletonList(5L));

        List<BookDto> bookDtos = bookDtoRepository.selectAll();

        Assertions.assertFalse(bookDtos.contains(expectedbBookDto));

        Assertions.assertTrue(bookDtos.contains(expected3));
        Assertions.assertTrue(bookDtos.contains(expected4));
        Assertions.assertTrue(bookDtos.contains(expected5));
    }
}