package ru.otus.hw7SpringData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw7SpringData.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorByName(String name);
}
