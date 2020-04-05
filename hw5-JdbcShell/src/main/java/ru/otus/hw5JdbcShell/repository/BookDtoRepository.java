package ru.otus.hw5JdbcShell.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw5JdbcShell.model.dto.BookDto;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.otus.hw5JdbcShell.utils.Utils.toLongList;


@Repository
public class BookDtoRepository {

    private static final RowMapper<BookDto> BOOK_DTO_ROW_MAPPER = (resultSet, i) -> new BookDto(
            resultSet.getLong("id"),
            resultSet.getString("name"),
            toLongList(resultSet.getArray("AUTHORS_ID").getArray()),
            toLongList(resultSet.getArray("GENRES_ID").getArray())
    );
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookDtoRepository(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    public static long generateLong() {
        return (long) (Math.random() * 10000000000L);
    }

    public Long insert(String name, List<Long> authorIds, List<Long> genreIds) {
        long id = generateLong();
        Map<String, Object> params = new java.util.HashMap<>();
        params.put("id", id);
        params.put("name", name);
        params.put("authorIds", authorIds);
        params.put("genreIds", genreIds);
        namedParameterJdbcOperations.update(
                "INSERT INTO BOOKS(ID, NAME, AUTHORS_ID, GENRES_ID) VALUES(:id, :name, (:authorIds), (:genreIds));",
                params
        );
        return id;
    }

    public BookDto select(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        try {
            return namedParameterJdbcOperations.queryForObject(
                    "select * from BOOKS where id = :id", params, BOOK_DTO_ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            // do nothing
        }
        return null;
    }

    public List<BookDto> selectAll() {
        try {
            return namedParameterJdbcOperations.query("select * from BOOKS", BOOK_DTO_ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            // do nothing
        }
        return Collections.emptyList();
    }

    public void update(long id, String name, List<Long> authorIds, List<Long> genreIds) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        params.put("authorIds", authorIds);
        params.put("genreIds", genreIds);
        namedParameterJdbcOperations.update(
                "update BOOKS set NAME = :name, AUTHORS_ID = (:authorIds), GENRES_ID = (:genreIds) where id = :id;",
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
