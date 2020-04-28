package ru.otus.hw8SpringDataNoSQL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw8SpringDataNoSQL.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorByName(String name);
}
