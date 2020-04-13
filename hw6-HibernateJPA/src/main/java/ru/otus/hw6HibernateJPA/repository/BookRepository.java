package ru.otus.hw6HibernateJPA.repository;

import org.springframework.stereotype.Repository;
import ru.otus.hw6HibernateJPA.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
public class BookRepository {

    @PersistenceContext
    private EntityManager em;


    public List<Book> selectAll() {
        return em.createQuery("select e from Book e", Book.class).getResultList();
    }


    public Book select(Long id) {
        TypedQuery<Book> query = em.createQuery(
                "select e from Book e where e.id = :id",
                Book.class
        )
                .setParameter("id", id);
        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }


    public void delete(Long id) {
        em.createQuery(
                "delete from Book e where e.id = :id"
        )
                .setParameter("id", id)
                .executeUpdate();
    }

    public void update(Long id, String name) {
        em.createQuery("update Book e set e.name = :name where e.id = :id")
                .setParameter("id", id)
                .setParameter("name", name)
                .executeUpdate();
    }

    public Long insert(Book book) {
        em.persist(book);
        return book.getId();
    }
}
