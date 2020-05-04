package ru.otus.hw9SpringMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.hw9SpringMVC.model.BookForm;
import ru.otus.hw9SpringMVC.service.BookFormService;
import ru.otus.hw9SpringMVC.service.LibraryService;

import java.util.Collections;

@Controller
public class BookController {

    private final LibraryService libraryService;
    private final BookFormService bookFormService;

    public BookController(LibraryService libraryService, BookFormService bookFormService) {
        this.libraryService = libraryService;
        this.bookFormService = bookFormService;
    }


    @GetMapping("/book/add_commentary")
    public String addCommentary() {
        return "bookAddCommentary";
    }

    @PutMapping("/book/add_commentary")
    public void addCommentary(
            @RequestParam("book_id") String bookId,
            @RequestParam("text") String text) {
        libraryService.addCommentary(bookId, text);
    }

    @GetMapping("/book/add")
    public String add(Model model) {
        model.addAttribute("book", new BookForm());
        return "bookAdd";
    }

    @PostMapping("/book/save")
    public RedirectView userSave(@ModelAttribute BookForm bookForm) {
        bookFormService.add(bookForm);
        return new RedirectView("/book/list", true);
    }

    @GetMapping("/book/update")
    public String update() {
        return "bookUpdate";
    }

    @PutMapping("/book/update")
    public void update(@RequestParam("book_id") String id, @ModelAttribute BookForm bookForm) {
        bookFormService.update(id, bookForm);
    }


    @GetMapping({"/", "/book/list"})
    public String getAll(Model model) {
        model.addAttribute("books", bookFormService.getAll());
        return "bookList";
    }

    @GetMapping("/book/get")
    public RedirectView get(@RequestParam("book_id") String id, Model model) {
        model.addAttribute("books", Collections.singletonList(libraryService.get(id)));
        return new RedirectView("/book/list", true);
    }


    @DeleteMapping("/book/delete")
    public RedirectView remove(String id) {
        libraryService.remove(id);
        return new RedirectView("/book/list", true);
    }
}
