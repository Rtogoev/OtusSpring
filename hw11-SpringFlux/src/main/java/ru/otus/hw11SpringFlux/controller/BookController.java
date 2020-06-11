package ru.otus.hw11SpringFlux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.hw11SpringFlux.model.BookForm;
import ru.otus.hw11SpringFlux.service.BookFormService;

import java.util.List;

@RestController
public class BookController {

    private final BookFormService bookFormService;

    public BookController(BookFormService bookFormService) {
        this.bookFormService = bookFormService;
    }

    @GetMapping("/book/get/all")
    public List<BookForm> getAll() {
        return bookFormService.getAll();
    }

}
