package ru.otus.hw16SpringActuator.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw16SpringActuator.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
