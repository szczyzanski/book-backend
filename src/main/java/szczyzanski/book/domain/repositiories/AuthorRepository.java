package szczyzanski.book.domain.repositiories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import szczyzanski.book.domain.entities.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
}
