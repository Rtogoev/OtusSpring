package ru.otus.hw9SpringMVC.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @PostMapping("/book/add_commentary")
    public RedirectView addCommentary(
            @RequestParam("book_id") String bookId,
            @RequestParam("text") String text) {
        libraryService.addCommentary(bookId.substring(1), text.substring(1));
        return new RedirectView("/book/list", true);
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
    public String update(Model model) {
        model.addAttribute("book", new BookForm());
        return "bookUpdate";
    }

    @PostMapping("/book/update")
    public RedirectView update(@ModelAttribute BookForm bookForm) {
        bookFormService.update(bookForm);
        return new RedirectView("/book/list", true);
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

    @PostMapping("/book/delete")
    public RedirectView remove(@RequestParam("id") String id) {
        // id.substring(1)  - костыль, которого я не смог избежать, потому что thymeleaf сам добавляет запятую вначале.
        libraryService.remove(id.substring(1));
        return new RedirectView("/book/list", true);
    }
}
