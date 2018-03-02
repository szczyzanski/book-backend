package szczyzanski.book.domain.repositiories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import szczyzanski.book.domain.entities.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
}
