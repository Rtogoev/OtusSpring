package ru.otus.hw11SpringFlux.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw11SpringFlux.model.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Optional<Genre> findByName(String name);
}
