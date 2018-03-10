package szczyzanski.book.api.controllers;
//TODO change from entities to DTOs
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    //TODO add exceptions
    @RequestMapping(value = "/{id}")
    public Author getOne(@PathVariable final Long id) {
        return authorService.getOne(id);
    }

    //TODO add exceptions
    @RequestMapping(value = "/add/{fName}&{sName}")
    public void addAuthor(@PathVariable final String fName, @PathVariable final String sName) {
        authorService.addAuthor(fName, sName);
    }
}
