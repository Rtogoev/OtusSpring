package ru.otus.hw5JdbcShell.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw5JdbcShell.model.dto.GenreDto;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDtoRepository {

    private static final RowMapper<GenreDto> GENRE_DTO_ROW_MAPPER = (resultSet, i) -> new GenreDto(
            resultSet.getLong("id"),
            resultSet.getString("name")
    );
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public GenreDtoRepository(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    public static long generateLong() {
        return (long) (Math.random() * 10000000000L);
    }

    public Long insert(String name) {
        long id = generateLong();
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        namedParameterJdbcOperations.update("INSERT INTO GENRES(id, name) VALUES(:id, :name);", params);
        return id;
    }

    public GenreDto select(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        return namedParameterJdbcOperations.queryForObject(
                "select * from GENRES where id = :id", params, GENRE_DTO_ROW_MAPPER
        );
    }

    public List<GenreDto> selectAll() {
        return namedParameterJdbcOperations.query("select * from GENRES", GENRE_DTO_ROW_MAPPER);
    }

    public void update(long id, String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        namedParameterJdbcOperations.update(
                "update GENRES set name = :name where id = :id;",
                params
        );
    }

    public void delete(long id) {
        Map<String, Object> params = Collections.singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from GENRES where id = :id",
                params
        );
    }
}
