package szczyzanski.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szczyzanski.book.domain.entities.Tag;
import szczyzanski.book.domain.repositiories.TagRepository;

import java.util.List;
import java.util.Set;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;

    public List<Tag> findAll() {
        return (List) tagRepository.findAll();
    }

    public Tag findByValue(String value) {
        return tagRepository.findByValue(value);
    }

    public Tag add(Tag tag) {
        return tagRepository.save(tag);
    }
}
