package ru.otus.hw10jQuery.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw10jQuery.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
