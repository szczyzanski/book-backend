package szczyzanski.book.api.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import szczyzanski.book.api.dto.TagDTO;
import szczyzanski.book.domain.entities.Tag;
import szczyzanski.book.services.TagService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/tags")
public class TagController {
    @Autowired
    TagService tagService;
    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/all")
    public List<TagDTO> findAll() {
        List<Tag> tagList = tagService.findAll();
        List<TagDTO> result = new ArrayList<>();
        for(Tag tag : tagList) {
            result.add(entityToDTO(tag));
        }
        return result;
    }

    private TagDTO entityToDTO(Tag tag) {
        if(tag == null) {
            return null;
        }
        return modelMapper.map(tag, TagDTO.class);
    }
}
