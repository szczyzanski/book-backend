package szczyzanski.book.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import szczyzanski.book.domain.entities.Author;
import szczyzanski.book.services.AuthorService;

import java.util.List;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @RequestMapping(value = "/all")
    public List<Author> findAll() {
        return authorService.findAll();
    }

    @RequestMapping(value = "/savedef")
    public void saveDefault() {
        authorService.saveDefault();
    }
}
