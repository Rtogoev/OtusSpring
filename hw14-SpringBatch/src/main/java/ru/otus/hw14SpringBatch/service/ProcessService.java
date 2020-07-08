package ru.otus.hw14SpringBatch.service;

import org.springframework.stereotype.Service;
import ru.otus.hw14SpringBatch.model.JdbcBook;
import ru.otus.hw14SpringBatch.model.MongoBook;

@Service
public class ProcessService {

    public JdbcBook process(MongoBook mongoBook) {
        return new JdbcBook(
                mongoBook.getId().hashCode(),
                mongoBook.getName(),
                mongoBook.getAuthor(),
                mongoBook.getGenre()
        );
    }
}
