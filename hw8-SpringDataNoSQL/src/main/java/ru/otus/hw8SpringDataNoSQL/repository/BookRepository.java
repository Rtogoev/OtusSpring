package ru.otus.hw8SpringDataNoSQL.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import ru.otus.hw8SpringDataNoSQL.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {
}
