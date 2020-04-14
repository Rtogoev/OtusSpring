package ru.otus.hw6HibernateJPA.repository;

import org.springframework.stereotype.Repository;
import ru.otus.hw6HibernateJPA.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


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

    public void update(Long id, String name) {
        em.merge(new Book(id, name));
    }

    public Long insert(Book Book) {
        em.persist(Book);
        return Book.getId();
    }

    public List<Book> selectAll() {
        return em.createQuery("select e from Book e", Book.class)
                .getResultList();
    }
}
