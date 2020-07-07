package ru.otus.hw14SpringBatch.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw14SpringBatch.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
