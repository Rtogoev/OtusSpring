package ru.otus.hw12Security.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw12Security.model.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByName(String name);
}
