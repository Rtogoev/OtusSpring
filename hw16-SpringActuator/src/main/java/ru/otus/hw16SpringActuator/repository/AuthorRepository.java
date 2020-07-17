package ru.otus.hw16SpringActuator.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw16SpringActuator.model.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByName(String name);
}
