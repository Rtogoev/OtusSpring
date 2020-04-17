package ru.otus.hw7SpringData.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.hw7SpringData.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long> {
    Genre findGenreByName(String name);
}
