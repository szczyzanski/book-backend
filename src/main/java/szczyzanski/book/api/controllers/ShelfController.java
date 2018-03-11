package szczyzanski.book.api.controllers;
//TODO change from entities to DTOs
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import szczyzanski.book.api.dto.ShelfDTO;
import szczyzanski.book.domain.entities.Book;
import szczyzanski.book.domain.entities.Shelf;
import szczyzanski.book.services.ShelfService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/shelves")
public class ShelfController {
    @Autowired
    private ShelfService shelfService;
    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/all")
    public List<ShelfDTO> findAll() {
        List<Shelf> shelfList = shelfService.findAll();
        List<ShelfDTO> result = new ArrayList<ShelfDTO>();
        for(Shelf shelf : shelfList) {
            result.add(entityToDTO(shelf));
        }
        return result;
    }

    @RequestMapping(value = "/savedef")
    public void saveDefault() {
        shelfService.saveDefault();
    }

    private ShelfDTO entityToDTO(final Shelf shelf) {
        if(shelf == null) {
            return null;
        }
        return modelMapper.map(shelf, ShelfDTO.class);
    }
}
