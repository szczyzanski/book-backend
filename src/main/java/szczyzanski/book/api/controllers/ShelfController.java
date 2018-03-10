package szczyzanski.book.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import szczyzanski.book.domain.entities.Shelf;
import szczyzanski.book.services.ShelfService;

import java.util.List;

@RestController
@RequestMapping(value = "/shelves")
public class ShelfController {
    @Autowired
    private ShelfService shelfService;

    @RequestMapping(value = "/all")
    public List<Shelf> findAll() {
        return this.shelfService.findAll();
    }

    @RequestMapping(value = "/savedef")
    public void saveDefault() {
        this.shelfService.saveDefault();
    }
}
