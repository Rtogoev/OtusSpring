package ru.otus.hw11SpringFlux.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw11SpringFlux.model.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByName(String name);
}
