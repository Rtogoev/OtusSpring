package ru.otus.hw5JdbcShell.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw5JdbcShell.model.BookDto;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDtoRepository {

    private static final RowMapper<BookDto> BOOK_DTO_ROW_MAPPER = (resultSet, i) -> new BookDto(
            resultSet.getLong("id"),
            resultSet.getString("name")
    );
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDtoRepository(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    public static long generateLong() {
        return (long) (Math.random() * 10000000000L);
    }

    public Long insert(String name) {
        long id = generateLong();
        Map<String, Object> params = new java.util.HashMap<>();
        params.put("id", id);
        params.put("name", name);
        namedParameterJdbcOperations.update("INSERT INTO BOOKS(id, name) VALUES(:id, :name);", params);
        return id;
    }

    public BookDto select(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from BOOKS where id = :id", params, BOOK_DTO_ROW_MAPPER
        );
    }

    public List<BookDto> selectAll() {
        return namedParameterJdbcOperations.query("select * from BOOKS", BOOK_DTO_ROW_MAPPER);
    }

    public void update(long id, String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        namedParameterJdbcOperations.update(
                "update BOOKS set name = :name where id = :id;",
                params
        );
    }

    public void delete(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from BOOKS where id = :id",
                params
        );
    }
}
