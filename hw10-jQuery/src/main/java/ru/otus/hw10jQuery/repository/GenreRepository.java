package ru.otus.hw10jQuery.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw10jQuery.model.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Optional<Genre> findByName(String name);
}
