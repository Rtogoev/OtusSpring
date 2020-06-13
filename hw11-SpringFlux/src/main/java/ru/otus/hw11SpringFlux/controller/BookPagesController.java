package ru.otus.hw11SpringFlux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import reactor.core.publisher.Mono;
import ru.otus.hw11SpringFlux.model.Book;
import ru.otus.hw11SpringFlux.repository.BookRepository;

@Controller
public class BookPagesController {

    private final BookRepository bookRepository;

    public BookPagesController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/book/add")
    public String add(Model model) {
        model.addAttribute("book", new Book());
        return "bookAdd";
    }

    @PostMapping("/book/save")
    public String save(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "bookList";
    }

    @GetMapping("/book/update")
    public String update(Model model) {
        model.addAttribute("book", new Book());
        return "bookUpdate";
    }

    @PostMapping("/book/update")
    public String update(@ModelAttribute Book book) {
        bookRepository.save(book);
        return "bookList";
    }


    @GetMapping({"/"})
    public String index() {
        return "bookList";
    }

    @PostMapping("/book/delete")
    public String remove(@RequestParam("id") String id) {
        // .substring(1)  - костыль, которого я не смог избежать, потому что thymeleaf сам добавляет запятую вначале.
        bookRepository.removeById(id);
        return "bookList";
    }
}
