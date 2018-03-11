package szczyzanski.book.api.controllers;
//TODO change from entities to DTOs
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import szczyzanski.book.api.dto.AuthorDTO;
import szczyzanski.book.domain.entities.Author;
import szczyzanski.book.services.AuthorService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private ModelMapper modelMapper;

    //TODO add exceptions
    @RequestMapping(value = "/all")
    public List<AuthorDTO> findAll() {
        List<Author> authorList = authorService.findAll();
        List<AuthorDTO> result = new ArrayList<AuthorDTO>();
        for(Author author : authorList) {
            result.add(entityToDTO(author));
        }
        return result;
    }

    //TODO add exceptions
    @RequestMapping(value = "/savedef")
    public void saveDefault() {
        authorService.saveDefault();
    }

    //TODO add exceptions
    @RequestMapping(value = "/{id}")
    public AuthorDTO getOne(@PathVariable final Long id) {
        return entityToDTO(authorService.getOne(id));
    }

    //TODO add exceptions
    @RequestMapping(value = "/add")
    public AuthorDTO add(final AuthorDTO authorDTO) {
        return entityToDTO(authorService.add(DTOToEntity(authorDTO)));
    }

    private AuthorDTO entityToDTO(final Author author) {
        if (author == null) {
            return null;
        }
        return modelMapper.map(author, AuthorDTO.class);
    }

    private Author DTOToEntity(final AuthorDTO authorDTO) {
        return new Author(authorDTO.getFirstName(), authorDTO.getSurname(), null);
    }
}
