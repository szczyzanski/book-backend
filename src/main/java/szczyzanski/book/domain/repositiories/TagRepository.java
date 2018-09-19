package szczyzanski.book.domain.repositiories;

import org.springframework.data.repository.CrudRepository;
import szczyzanski.book.domain.entities.Tag;

import java.util.Set;

public interface TagRepository extends CrudRepository<Tag, Long> {
    Tag findByValue(final String value);
}
