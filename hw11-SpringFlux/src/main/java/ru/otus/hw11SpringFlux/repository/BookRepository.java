package ru.otus.hw11SpringFlux.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw11SpringFlux.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
