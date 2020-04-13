package ru.otus.hw6HibernateJPA.service;

import org.springframework.stereotype.Service;
import ru.otus.hw6HibernateJPA.model.Book;
import ru.otus.hw6HibernateJPA.repository.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Long add(String name) {
       return bookRepository.insert(new Book(name));
    }

    public Book get(Long id) {
        return bookRepository.select(id);
    }

    public void update(Long id,String name){
        bookRepository.update(id,name);
    }

    public void delete(Long id){
        bookRepository.delete(id);
    }

    public List<Book> getAll() {
        return bookRepository.selectAll();
    }
}
