package ru.otus.hw7SpringData.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.hw7SpringData.model.Book;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager em;

    public Book select(Long id) {
        return em.find(Book.class, id);
    }


    public void delete(Long id) {
        em.createQuery(
                "delete from Book e where e.id = :id"
        )
                .setParameter("id", id)
                .executeUpdate();
        em.clear();
    }

    public void update(Book update) {
        em.merge(update);
    }

    public Long insert(Book Book) {
        em.persist(Book);
        return Book.getId();
    }

    public List<Book> selectAll() {
        EntityGraph<?> commentaryGraph = em.getEntityGraph("commentary-graph");

        return em.createQuery("select e from Book e", Book.class)
                .setHint("javax.persistent.fetchgraph",commentaryGraph)
                .getResultList();
    }
}
