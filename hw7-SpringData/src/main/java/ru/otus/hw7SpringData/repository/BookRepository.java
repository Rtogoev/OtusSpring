package ru.otus.hw7SpringData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw7SpringData.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
}
