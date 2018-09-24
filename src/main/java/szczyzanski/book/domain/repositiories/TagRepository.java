package szczyzanski.book.domain.repositiories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import szczyzanski.book.domain.entities.Tag;

import java.util.Set;

public interface TagRepository extends CrudRepository<Tag, Long> {
    Tag findByValue(final String value);

    @Query(value = "SELECT t FROM Tag t INNER JOIN t.bookSet b WHERE b.id = :book_id")
    Set<Tag> findByBookId(@Param("book_id") final long bookId);
}
