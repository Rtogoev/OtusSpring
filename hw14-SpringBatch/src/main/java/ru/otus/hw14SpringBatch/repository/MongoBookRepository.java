package ru.otus.hw14SpringBatch.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw14SpringBatch.model.MongoBook;

public interface MongoBookRepository extends MongoRepository<MongoBook, String> {
}
