package szczyzanski.book.domain.repositiories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import szczyzanski.book.domain.entities.Author;

import java.util.Set;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    @Query(value = "SELECT * FROM authors WHERE authors.forname = :forname AND authors.surname = :surname", nativeQuery = true)
    Author findByName(@Param("forname") final String forname,
                           @Param("surname") final String surname);

    @Query(value = "SELECT a FROM Author a INNER JOIN a.bookSet b WHERE b.id = :book_id")
    Set<Author> findByBookId(@Param("book_id") final long bookId);
}
