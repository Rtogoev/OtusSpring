package ru.otus.hw8SpringDataNoSQL.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw8SpringDataNoSQL.model.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByName(String name);
}
