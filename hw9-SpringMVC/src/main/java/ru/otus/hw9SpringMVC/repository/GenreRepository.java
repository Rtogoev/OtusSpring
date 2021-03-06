package ru.otus.hw9SpringMVC.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw9SpringMVC.model.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Optional<Genre> findByName(String name);
}
