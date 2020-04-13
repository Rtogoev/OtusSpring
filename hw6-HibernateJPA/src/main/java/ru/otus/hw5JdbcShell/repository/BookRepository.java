package ru.otus.hw5JdbcShell.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import ru.otus.hw5JdbcShell.model.dto.Book;

import java.util.*;

import static java.util.Collections.singletonMap;
import static ru.otus.hw5JdbcShell.utils.Utils.generateLong;


@Repository
public class BookRepository {

    private static final RowMapper<Book> BOOK_DTO_ROW_MAPPER = (resultSet, i) -> new Book(
            resultSet.getLong("id"),
            resultSet.getString("name")
    );
    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookRepository(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    public Long insert(String name) {
        long id = generateLong();
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        namedParameterJdbcOperations.update(
                "INSERT INTO BOOKS(ID, NAME) VALUES(:id, :name);",
                params
        );
        return id;
    }

    public Book select(long id) {
        Map<String, Object> params = singletonMap("id", id);
        try {
            return namedParameterJdbcOperations.queryForObject(
                    "select * from BOOKS where id = :id", params, BOOK_DTO_ROW_MAPPER
            );
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public Set<Book> selectAll() {
        try {
            return new HashSet<>(namedParameterJdbcOperations.query("select * from BOOKS", BOOK_DTO_ROW_MAPPER));
        } catch (EmptyResultDataAccessException e) {
            return Collections.emptySet();
        }
    }

    public void update(long id, String name) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("name", name);
        namedParameterJdbcOperations.update(
                "update BOOKS set NAME = :name where id = :id;",
                params
        );
    }

    public void delete(long id) {
        Map<String, Object> params = singletonMap("id", id);
        namedParameterJdbcOperations.update(
                "delete from BOOKS where id = :id",
                params
        );
    }
}
