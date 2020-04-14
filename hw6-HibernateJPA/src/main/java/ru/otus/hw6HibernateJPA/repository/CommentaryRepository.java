package ru.otus.hw6HibernateJPA.repository;

import org.springframework.stereotype.Repository;
import ru.otus.hw6HibernateJPA.model.Commentary;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Set;

@Repository
public class CommentaryRepository {

    @PersistenceContext
    private EntityManager em;

    public Commentary select(Long id) {
        return em.find(Commentary.class, id);
    }

    public Commentary select(String text) {
        return em.createQuery("select e from Commentary e where e.text = :text", Commentary.class)
                .setParameter("text", text)
                .getSingleResult();
    }


    public void delete(Long id) {
        em.createQuery(
                "delete from Commentary e where e.id = :id"
        )
                .setParameter("id", id)
                .executeUpdate();
        em.clear();
    }

    public void update(Long id, String text) {
        em.merge(new Commentary(id, text));
    }

    public Long insert(Commentary commentary) {
        em.persist(commentary);
        return commentary.getId();
    }

    public List<Commentary> select(Set<Long> ids) {
     return em.createQuery("select e from Commentary e where e.id in :ids", Commentary.class)
             .setParameter("ids", ids)
             .getResultList();
    }
}
