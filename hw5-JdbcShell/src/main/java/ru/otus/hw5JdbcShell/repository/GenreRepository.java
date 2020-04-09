package ru.otus.hw5JdbcShell.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw5JdbcShell.model.dto.Genre;

import java.util.*;

import static java.util.Collections.singletonMap;
import static ru.otus.hw5JdbcShell.utils.Utils.generateLong;

@Repository
public class GenreRepository {

    private static final RowMapper<Genre> GENRE_DTO_ROW_MAPPER = (resultSet, i) -> new Genre(
            resultSet.getLong("id"),
            resultSet.getString("name")
    );
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;
private final JdbcTemplate jdbcTemplate;
    public GenreRepository(NamedParameterJdbcOperations namedParameterJdbcOperations, JdbcTemplate jdbcTemplate) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long insert(String name) {
        long id = generateLong();
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        namedParameterJdbcOperations.update("INSERT INTO GENRES(id, name) VALUES(:id, :name);", params);
        return id;
    }

    public Genre select(long id) {
        Map<String, Object> params = singletonMap("id", id);
        try {
            return namedParameterJdbcOperations.queryForObject(
                    "select * from GENRES where id = :id", params, GENRE_DTO_ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Genre select(String name) {
        Map<String, Object> params = singletonMap("name", name);
        try {
            return namedParameterJdbcOperations.queryForObject(
                    "select * from GENRES where name = :name", params, GENRE_DTO_ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public List<Genre> selectAll() {
        try {
            return namedParameterJdbcOperations.query("select * from GENRES", GENRE_DTO_ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
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
        Map<String, Object> params = singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from GENRES where id = :id",
                params
        );
    }

        public List<Genre> select(Set<Long> genreIds) {
            Map<String, Object> params = new HashMap<>();
            params.put("ids", genreIds);
            try {
                return namedParameterJdbcOperations.query("select * from GENRES where ID IN (:ids)", params, GENRE_DTO_ROW_MAPPER);
            } catch (EmptyResultDataAccessException e) {
                return Collections.emptyList();
            }
    }
}
