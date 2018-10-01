package szczyzanski.book.domain.repositiories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import szczyzanski.book.domain.entities.AuthorWithBookSetPower;

@Repository
public interface AuthorWithBookSetPowerRepository extends CrudRepository<AuthorWithBookSetPower, Long> {
    @Query(value = "SELECT * FROM authors_with_bs_power ORDER BY authors_with_bs_power.bs_power LIMIT 1",
                        nativeQuery = true)
    public AuthorWithBookSetPower getAuthorWithLastBSPower();

    @Query(value = "SELECT a FROM AuthorWithBookSetPower a WHERE a.forname = :forname AND a.surname = :surname")
    AuthorWithBookSetPower findByName(@Param("forname") final String forname,
                                        @Param("surname") final String surname);
}
