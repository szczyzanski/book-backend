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

    public Tag findById(final long id) {
        return tagRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public Set<Tag> findByBookId(final long id) {
        return tagRepository.findByBookId(id);
    }
}
