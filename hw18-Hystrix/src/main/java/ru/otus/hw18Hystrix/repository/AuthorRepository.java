package ru.otus.hw18Hystrix.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw18Hystrix.model.Author;

import java.util.Optional;

public interface AuthorRepository extends MongoRepository<Author, String> {
    Optional<Author> findByName(String name);
}
