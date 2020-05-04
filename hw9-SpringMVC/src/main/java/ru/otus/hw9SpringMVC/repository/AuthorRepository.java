package ru.otus.hw9SpringMVC.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw9SpringMVC.model.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByName(String name);
}
