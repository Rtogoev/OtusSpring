package ru.otus.hw14SpringBatch.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw14SpringBatch.model.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Optional<Genre> findByName(String name);
}
