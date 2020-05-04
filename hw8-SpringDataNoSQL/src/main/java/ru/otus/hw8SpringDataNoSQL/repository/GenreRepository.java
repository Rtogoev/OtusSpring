package ru.otus.hw8SpringDataNoSQL.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw8SpringDataNoSQL.model.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Optional<Genre> findByName(String name);
}
