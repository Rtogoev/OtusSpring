package ru.otus.hw5JdbcShell.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.hw5JdbcShell.model.BookDto;

import java.util.List;

@JdbcTest
@Import(BookDtoRepository.class)
class BookDtoRepositoryTest {
    @Autowired
    private BookDtoRepository bookDtoRepository;

    @Test
    void testAll() {
        String name = "test";
        Long id = bookDtoRepository.insert(name);

        BookDto expectedbBookDto = new BookDto(id, name);

        Assertions.assertEquals(
                expectedbBookDto,
                bookDtoRepository.select(expectedbBookDto.getId())
        );

        expectedbBookDto = new BookDto(expectedbBookDto.getId(), "test2");

        bookDtoRepository.update(expectedbBookDto.getId(), expectedbBookDto.getName());

        Assertions.assertEquals(
                expectedbBookDto,
                bookDtoRepository.select(expectedbBookDto.getId())
        );

        bookDtoRepository.delete(expectedbBookDto.getId());

        Long id3 = bookDtoRepository.insert("test3");
        Long id4 = bookDtoRepository.insert("test4");
        Long id5 = bookDtoRepository.insert("test5");

        BookDto expected3 = new BookDto(id3, "test3");
        BookDto expected4 = new BookDto(id4, "test4");
        BookDto expected5 = new BookDto(id5, "test5");

        List<BookDto> bookDtos = bookDtoRepository.selectAll();

        Assertions.assertFalse(bookDtos.contains(expectedbBookDto));

        Assertions.assertTrue(bookDtos.contains(expected3));
        Assertions.assertTrue(bookDtos.contains(expected4));
        Assertions.assertTrue(bookDtos.contains(expected5));
    }
}