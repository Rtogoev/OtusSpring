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
public class BookGenreRepository {

    private final NamedParameterJdbcOperations namedParameterJdbcOperations;

    public BookGenreRepository(NamedParameterJdbcOperations namedParameterJdbcOperations) {
        this.namedParameterJdbcOperations = namedParameterJdbcOperations;
    }

    public void insert(Long bookId, Long genreId) {
        Map<String, Object> params = new HashMap<>();
        params.put("book_id", bookId);
        params.put("genre_id", genreId);
        namedParameterJdbcOperations.update("INSERT INTO BOOK_GENRE(BOOK_ID, GENRE_ID) VALUES(:book_id, :genre_id);", params);
    }

    public Set<Long> select(long bookId) {
        Map<String, Object> params = singletonMap("book_id", bookId);
        try {
            return new HashSet<>(
                    namedParameterJdbcOperations.queryForList(
                            "select GENRE_ID from BOOK_GENRE where BOOK_ID = :book_id", params, Long.class
                    )
            );
        } catch (EmptyResultDataAccessException e) {
            return new HashSet<>();
        }
    }

    public void delete(long bookId) {
        Map<String, Object> params = singletonMap("book_id", bookId);
        namedParameterJdbcOperations.update(
                "delete from BOOK_GENRE where BOOK_ID = :book_id",
                params
        );
    }
}
