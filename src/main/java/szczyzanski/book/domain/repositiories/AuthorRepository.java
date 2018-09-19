package szczyzanski.book.domain.repositiories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import szczyzanski.book.domain.entities.Author;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    @Query(value = "SELECT * FROM authors WHERE authors.forname = :forname AND authors.surname = :surname", nativeQuery = true)
    Author findByName(@Param("forname") final String forname,
                           @Param("surname") final String surname);
}
