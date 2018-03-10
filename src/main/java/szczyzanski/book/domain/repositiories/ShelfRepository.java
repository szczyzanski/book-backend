package szczyzanski.book.domain.repositiories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import szczyzanski.book.domain.entities.Shelf;

@Repository
public interface ShelfRepository extends CrudRepository<Shelf, Long> {
}
