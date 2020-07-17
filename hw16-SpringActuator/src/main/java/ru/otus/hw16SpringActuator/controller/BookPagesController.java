package ru.otus.hw16SpringActuator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;
import ru.otus.hw16SpringActuator.model.BookForm;
import ru.otus.hw16SpringActuator.service.BookFormService;
import ru.otus.hw16SpringActuator.service.LibraryService;

@Controller
public class BookPagesController {

    private final LibraryService libraryService;
    private final BookFormService bookFormService;

    public BookPagesController(LibraryService libraryService, BookFormService bookFormService) {
        this.libraryService = libraryService;
        this.bookFormService = bookFormService;
    }

    @PostMapping("/book/add_commentary")
    public RedirectView addCommentary(
            @RequestParam("book_id") String bookId,
            @RequestParam("text") String text) {
        // .substring(1)  - костыль, которого я не смог избежать, потому что thymeleaf сам добавляет запятую вначале.
        libraryService.addCommentary(bookId.substring(1), text.substring(1));
        return new RedirectView("/", true);
    }

    @GetMapping("/book/add")
    public String add(Model model) {
        model.addAttribute("book", new BookForm());
        return "bookAdd";
    }

    @PostMapping("/book/save")
    public RedirectView userSave(@ModelAttribute BookForm bookForm) {
        bookFormService.add(bookForm);
        return new RedirectView("/", true);
    }

    @GetMapping("/book/update")
    public String update(Model model) {
        model.addAttribute("book", new BookForm());
        return "bookUpdate";
    }

    @PostMapping("/book/update")
    public RedirectView update(@ModelAttribute BookForm bookForm) {
        bookFormService.update(bookForm);
        return new RedirectView("/", true);
    }


    @GetMapping({"/"})
    public String index() {
        return "bookList";
    }

    @PostMapping("/book/delete")
    public RedirectView remove(@RequestParam("id") String id) {
        // .substring(1)  - костыль, которого я не смог избежать, потому что thymeleaf сам добавляет запятую вначале.
        libraryService.remove(id.substring(1));
        return new RedirectView("/", true);
    }
}
