package ru.otus.hw11SpringFlux.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.otus.hw11SpringFlux.model.Book;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {

    void removeById(String id);

    Flux<Book> findAll();
}
