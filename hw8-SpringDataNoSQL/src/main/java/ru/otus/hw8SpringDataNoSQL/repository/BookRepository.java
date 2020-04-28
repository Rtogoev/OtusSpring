package ru.otus.hw8SpringDataNoSQL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw8SpringDataNoSQL.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
