package szczyzanski.book.api.controllers;
//TODO change from entities to DTOs
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import szczyzanski.book.api.dto.AuthorDTO;
import szczyzanski.book.api.dto.AuthorWithBookSetPowerDTO;
import szczyzanski.book.domain.entities.Author;
import szczyzanski.book.domain.entities.AuthorWithBookSetPower;
import szczyzanski.book.services.AuthorService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<AuthorDTO> findAll() {
        List<Author> authorList = authorService.findAll();
        List<AuthorDTO> result = new ArrayList<AuthorDTO>();
        for(Author author : authorList) {
            result.add(entityToDTO(author));
        }
        return result;
    }

    @RequestMapping(value = "/name")
    public Author findByName() {
        return authorService.findByName("Jason", "Mistyczny");
    }

    @RequestMapping(value = "/book/{bookId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Set<AuthorDTO> findyByBookId(@PathVariable final long bookId) {
        Set<AuthorDTO> authors = new HashSet<>();
        for(Author author : authorService.findByBookId(bookId)) {
            authors.add(entityToDTO(author));
        }
        return authors;
    }

    @RequestMapping(value = "/top")
    @CrossOrigin(origins = "http://localhost:4200")
    public Set<AuthorWithBookSetPowerDTO> getMostPopular() {
        Set<AuthorWithBookSetPowerDTO> authors = new HashSet<>();
        for(AuthorWithBookSetPower author : authorService.getMostPopular()) {
            authors.add(entityToDTO(author));
        }
        return authors;
    }

    private AuthorWithBookSetPowerDTO entityToDTO(final AuthorWithBookSetPower author) {
        if (author == null) {
            return null;
        }
        return modelMapper.map(author, AuthorWithBookSetPowerDTO.class);
    }

    private AuthorDTO entityToDTO(final Author author) {
        if (author == null) {
            return null;
        }
        return modelMapper.map(author, AuthorDTO.class);
    }

    private Author DTOToEntity(final AuthorDTO authorDTO) {
        return new Author(authorDTO.getForname(), authorDTO.getSurname(), null);
    }
}
