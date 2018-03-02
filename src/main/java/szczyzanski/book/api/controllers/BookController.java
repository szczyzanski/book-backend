package szczyzanski.book.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import szczyzanski.book.domain.entities.Book;
import szczyzanski.book.services.BookService;

import java.util.ArrayList;

@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @RequestMapping(value = "/all")
    public ArrayList<Book> findAll() {
        return bookService.findAll();
    }
}
