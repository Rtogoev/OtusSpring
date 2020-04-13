package ru.otus.hw6HibernateJPA.repository;

import org.springframework.stereotype.Repository;
import ru.otus.hw6HibernateJPA.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    public Author select(Long id) {
        return em.find(Author.class, id);
    }

    public Author select(String name) {
        return em.createQuery("select e from Author e where e.name = :name", Author.class)
                .setParameter("name", name)
                .getSingleResult();
    }


    public void delete(Long id) {
        em.createQuery(
                "delete from Author e where e.id = :id"
        )
                .setParameter("id", id)
                .executeUpdate();
    }

    public void update(Long id, String name) {
        em.merge(new Author(id, name));
    }

    public Long insert(Author Author) {
        em.persist(Author);
        return Author.getId();
    }

    public List<Author> select(Set<Long> ids) {
     return em.createQuery("select e from Author e where e.id in :ids", Author.class)
             .setParameter("ids", ids)
             .getResultList();
    }
}
