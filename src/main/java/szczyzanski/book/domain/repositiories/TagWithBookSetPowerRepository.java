package szczyzanski.book.domain.repositiories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import szczyzanski.book.domain.entities.TagWithBookSetPower;

@Repository
public interface TagWithBookSetPowerRepository extends CrudRepository<TagWithBookSetPower, Long> {
    TagWithBookSetPower findByValue(final String value);

    @Query(value = "SELECT * FROM tags_with_bs_power ORDER BY tags_with_bs_power.bs_power LIMIT 1",
            nativeQuery = true)
    TagWithBookSetPower getTagWithLastBSPower();
}
