package ru.otus.hw9SpringMVC.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw9SpringMVC.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
