package ru.otus.hw6HibernateJPA.repository;

import org.springframework.stereotype.Repository;
import ru.otus.hw6HibernateJPA.model.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class GenreRepository {

    @PersistenceContext
    private EntityManager em;

    public Genre select(Long id) {
        return em.find(Genre.class, id);
    }

    public Genre select(String name) {
        return em.createQuery("select e from Genre e where e.name = :name", Genre.class)
                .setParameter("name", name)
                .getSingleResult();
    }


    public void delete(Long id) {
        em.createQuery(
                "delete from Genre e where e.id = :id"
        )
                .setParameter("id", id)
                .executeUpdate();
        em.clear();
    }

    public void update(Long id, String name) {
        em.merge(new Genre(id, name));
    }

    public Long insert(Genre genre) {
        em.persist(genre);
        return genre.getId();
    }

    public List<Genre> select(Set<Long> ids) {
        return em.createQuery("select e from Genre e where e.id in :ids", Genre.class)
                .setParameter("ids", ids)
                .getResultList();
    }
}
