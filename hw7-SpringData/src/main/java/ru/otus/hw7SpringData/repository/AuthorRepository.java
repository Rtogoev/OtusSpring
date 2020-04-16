package ru.otus.hw7SpringData.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw7SpringData.model.Author;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Transactional
@Repository
public class AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    public Author select(Long id) {
        return em.find(Author.class, id);
    }

    public Author select(String name) {
        try {
            return em.createQuery("select e from Author e where e.name = :name", Author.class)
                    .setParameter("name", name)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public void delete(Long id) {
        em.createQuery(
                "delete from Author e where e.id = :id"
        )
                .setParameter("id", id)
                .executeUpdate();
        em.clear();
    }

    public void update(Long id, String name) {
        em.merge(new Author(id, name));
    }

    public Long insert(Author author) {
        em.persist(author);
        return author.getId();
    }

    public List<Author> select(Set<Long> ids) {
        return em.createQuery("select e from Author e where e.id in :ids", Author.class)
                .setParameter("ids", ids)
                .getResultList();
    }
}
