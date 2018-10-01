package szczyzanski.book.domain.repositiories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import szczyzanski.book.domain.entities.Book;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {
    @Query(value = "SELECT b FROM Book b WHERE b.id > ((SELECT COUNT(*) FROM Book) - :n)")
    List<Book> getLastNBooks(@Param("n") Long n);
}
