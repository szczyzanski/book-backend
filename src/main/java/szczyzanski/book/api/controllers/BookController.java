package szczyzanski.book.api.controllers;
//TODO change from entities to DTOs
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import szczyzanski.book.domain.entities.Book;
import szczyzanski.book.services.BookService;
import szczyzanski.book.services.ShelfService;

import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private ShelfService shelfService;

    @RequestMapping(value = "/all")
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @RequestMapping(value = "/savedef")
    public void saveDefault() {
        bookService.saveDefault(shelfService.findById());
    }
}
