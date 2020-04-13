package ru.otus.hw5JdbcShell.repository;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static java.util.Collections.singletonMap;

@Repository
public class BookAuthorRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookAuthorRepository(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    public void insert(Long bookId, Long authorId) {
        Map<String, Object> params = new HashMap<>();
        params.put("book_id", bookId);
        params.put("author_id", authorId);
        namedParameterJdbcOperations.update("INSERT INTO BOOK_AUTHOR(BOOK_ID, AUTHOR_ID) VALUES(:book_id, :author_id);", params);
    }

    public Set<Long> select(long bookId) {
        Map<String, Object> params = singletonMap("book_id", bookId);
        try {
            return new HashSet<>(
                    namedParameterJdbcOperations.queryForList(
                            "select AUTHOR_ID from BOOK_AUTHOR where BOOK_ID = :book_id", params, Long.class
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return new HashSet<>();
        }
    }

    public void delete(long bookId) {
        Map<String, Object> params = singletonMap("book_id", bookId);
        namedParameterJdbcOperations.update(
                "delete from BOOK_AUTHOR where BOOK_ID = :book_id",
                params
        );
    }
}
