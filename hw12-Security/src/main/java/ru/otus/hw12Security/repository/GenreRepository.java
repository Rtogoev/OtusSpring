package ru.otus.hw12Security.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw12Security.model.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Optional<Genre> findByName(String name);
}
