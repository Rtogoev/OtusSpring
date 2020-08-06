package ru.otus.hw18Hystrix.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw18Hystrix.model.Genre;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {
    Optional<Genre> findByName(String name);
}
