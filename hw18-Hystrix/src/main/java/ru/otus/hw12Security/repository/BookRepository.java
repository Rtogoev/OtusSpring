package ru.otus.hw12Security.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw12Security.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
