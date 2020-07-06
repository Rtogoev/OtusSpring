package ru.otus.hw11SpringFlux.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.result.view.RedirectView;
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
    public RedirectView save(@ModelAttribute Book book) {
        bookRepository.save(book).subscribe();
        return new RedirectView("/");
    }

    @GetMapping("/book/update")
    public String update(Model model) {
        model.addAttribute("book", new Book());
        return "bookUpdate";
    }

    @PostMapping("/book/update")
    public RedirectView update(@ModelAttribute Book book) {
        bookRepository.save(book).subscribe();
        return new RedirectView("/");
    }


    @GetMapping("/")
    public String index() {
        return "bookList";
    }

    @PostMapping("/book/delete")
    public RedirectView remove(@RequestParam("id") String id) {
        System.out.println("id:" + id);
        bookRepository.deleteById(id).subscribe();
        return new RedirectView("/");
    }
}
