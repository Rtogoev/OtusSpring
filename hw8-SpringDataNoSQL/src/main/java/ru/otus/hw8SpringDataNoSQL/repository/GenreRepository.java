package ru.otus.hw8SpringDataNoSQL.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw8SpringDataNoSQL.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findGenreByName(String name);
}
