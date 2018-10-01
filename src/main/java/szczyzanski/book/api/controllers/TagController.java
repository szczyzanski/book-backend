package szczyzanski.book.api.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import szczyzanski.book.api.dto.TagDTO;
import szczyzanski.book.api.dto.TagWithBookSetPowerDTO;
import szczyzanski.book.domain.entities.Tag;
import szczyzanski.book.domain.entities.TagWithBookSetPower;
import szczyzanski.book.services.TagService;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value = "/tags")
public class TagController {
    @Autowired
    TagService tagService;
    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/all")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<TagDTO> findAll() {
        List<Tag> tagList = tagService.findAll();
        List<TagDTO> result = new ArrayList<>();
        for(Tag tag : tagList) {
            result.add(entityToDTO(tag));
        }
        return result;
    }

    @RequestMapping(value = "/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public TagDTO findById(@PathVariable final long id) {
        return entityToDTO(tagService.findById(id));
    }

    @RequestMapping(value = "/book/{bookId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public Set<TagDTO> findyByBookId(@PathVariable final long bookId) {
        Set<TagDTO> authors = new HashSet<>();
        for(Tag tag : tagService.findByBookId(bookId)) {
            authors.add(entityToDTO(tag));
        }
        return authors;
    }

    @RequestMapping(value = "/top")
    public Set<TagWithBookSetPowerDTO> getMostPopular() {
        Set<TagWithBookSetPowerDTO> tags = new HashSet<>();
        for(TagWithBookSetPower tag : tagService.getMostPopular()) {
            tags.add(entityToDTO(tag));
        }
        return tags;
    }

    private TagDTO entityToDTO(Tag tag) {
        if(tag == null) {
            return null;
        }
        return modelMapper.map(tag, TagDTO.class);
    }

    private TagWithBookSetPowerDTO entityToDTO(TagWithBookSetPower tag) {
        if(tag == null) {
            return null;
        }
        return modelMapper.map(tag, TagWithBookSetPowerDTO.class);
    }
}
