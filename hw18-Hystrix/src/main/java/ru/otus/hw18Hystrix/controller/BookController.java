package ru.otus.hw18Hystrix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.hw18Hystrix.model.BookForm;
import ru.otus.hw18Hystrix.service.BookFormService;
import ru.otus.hw18Hystrix.service.LibraryService;

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
        // .substring(1)  - костыль, которого я не смог избежать, потому что thymeleaf сам добавляет запятую вначале.
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

    @GetMapping("/book/change")
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

    @PostMapping("/book/delete")
    public RedirectView remove(@RequestParam("id") String id) {
        // .substring(1)  - костыль, которого я не смог избежать, потому что thymeleaf сам добавляет запятую вначале.
        libraryService.remove(id.substring(1));
        return new RedirectView("/book/list", true);
    }
}
