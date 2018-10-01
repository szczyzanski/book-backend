package szczyzanski.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import szczyzanski.book.domain.entities.Tag;
import szczyzanski.book.domain.entities.TagWithBookSetPower;
import szczyzanski.book.domain.repositiories.TagRepository;
import szczyzanski.book.domain.repositiories.TagWithBookSetPowerRepository;

import java.util.List;
import java.util.Set;

@Service
public class TagService {
    @Autowired
    TagRepository tagRepository;
    @Autowired
    TagWithBookSetPowerRepository tagWithBookSetPowerRepository;

    public List<Tag> findAll() {
        return (List) tagRepository.findAll();
    }

    public Tag findByValue(String value) {
        return tagRepository.findByValue(value);
    }

    public Tag save(Tag tag) {
        return tagRepository.save(tag);
    }

    public void save(Set<Tag> tags) {
        for(Tag tag : tags) {
            tagRepository.save(tag);
            TagWithBookSetPower tagWithBookSetPower = tagWithBookSetPowerRepository
                                                        .findByValue(tag.getValue());
            if(tagWithBookSetPowerRepository.count() > 9) {
                TagWithBookSetPower lastTag = tagWithBookSetPowerRepository.getTagWithLastBSPower();
                if(tag.getBookSetPower() > lastTag.getBookSetPower()) {
                    if(tagWithBookSetPower == null) {
                        tagWithBookSetPowerRepository.save(new TagWithBookSetPower(tag));
                    } else {
                        tagWithBookSetPower.setBookSetPower(tagWithBookSetPower.getBookSetPower() + 1);
                        tagWithBookSetPowerRepository.save(tagWithBookSetPower);
                    }
                    tagWithBookSetPowerRepository.delete(lastTag);
                }
            } else {
                if(tagWithBookSetPower == null) {
                    tagWithBookSetPowerRepository.save(new TagWithBookSetPower(tag));
                } else {
                    tagWithBookSetPower.setBookSetPower(tagWithBookSetPower.getBookSetPower() + 1);
                    tagWithBookSetPowerRepository.save(tagWithBookSetPower);
                }
            }
        }
    }

    public Tag findById(final long id) {
        return tagRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public Set<Tag> findByBookId(final long id) {
        return tagRepository.findByBookId(id);
    }

    public List<TagWithBookSetPower> getMostPopular() {
        return (List) tagWithBookSetPowerRepository.findAll();
    }

}
