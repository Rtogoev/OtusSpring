package ru.otus.hw18Hystrix.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw18Hystrix.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
