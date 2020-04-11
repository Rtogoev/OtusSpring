package ru.otus.hw5JdbcShell.service;

import org.springframework.stereotype.Service;
import ru.otus.hw5JdbcShell.model.dto.Book;
import ru.otus.hw5JdbcShell.repository.BookRepository;

import java.util.Set;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Long add(String name) {
       return bookRepository.insert(name);
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

    public Set<Book> getAll() {
        return bookRepository.selectAll();
    }
}
