package szczyzanski.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szczyzanski.book.domain.entities.Shelf;
import szczyzanski.book.domain.repositiories.ShelfRepository;

import java.util.List;

@Service
public class ShelfService {
    @Autowired
    private ShelfRepository shelfRepository;

    public List<Shelf> findAll() {
        return (List) shelfRepository.findAll();
    }

    public void saveDefault() {
        shelfRepository.save(new Shelf(1, 1, 1));
    }

    //TEST
    public Shelf findById(final Long id) {
        return shelfRepository.findById(id).get();
    }
}
