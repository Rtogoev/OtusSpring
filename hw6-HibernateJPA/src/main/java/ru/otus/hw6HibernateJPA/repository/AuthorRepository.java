package ru.otus.hw6HibernateJPA.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw6HibernateJPA.model.Author;

import java.util.*;

import static java.util.Collections.singletonMap;
import static ru.otus.hw6HibernateJPA.utils.Utils.generateLong;

@Repository
public class AuthorRepository {

    private static final RowMapper<Author> AUTHOR_DTO_ROW_MAPPER = (resultSet, i) -> new Author(
            resultSet.getLong("id"),
            resultSet.getString("name")
    );
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public AuthorRepository(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    public Long insert(String name) {
        long id = generateLong();
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        namedParameterJdbcOperations.update("INSERT INTO AUTHORS(id, name) VALUES(:id, :name);", params);
        return id;
    }

    public Author select(long id) {
        Map<String, Object> params = singletonMap("id", id);
        try {
            return namedParameterJdbcOperations.queryForObject(
                    "select * from AUTHORS where id = :id", params, AUTHOR_DTO_ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Author select(String name) {
        Map<String, Object> params = singletonMap("name", name);
        try {
            return namedParameterJdbcOperations.queryForObject(
                    "select * from AUTHORS where name = :name", params, AUTHOR_DTO_ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void update(long id, String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        namedParameterJdbcOperations.update(
                "update AUTHORS set name = :name where id = :id;",
                params
        );
    }

    public void delete(long id) {
        Map<String, Object> params = singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from AUTHORS where id = :id",
                params
        );
    }

    public List<Author> select(Set<Long> authorIds) {
        Map<String, Object> params = new HashMap<>();
        params.put("ids", authorIds);
        try {
            return namedParameterJdbcOperations.query("select * from AUTHORS where ID IN (:ids)", params, AUTHOR_DTO_ROW_MAPPER);
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptyList();
        }
    }
}
