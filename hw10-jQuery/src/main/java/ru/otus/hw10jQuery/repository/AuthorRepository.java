package ru.otus.hw10jQuery.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw10jQuery.model.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByName(String name);
}
