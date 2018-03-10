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
        return (List) this.shelfRepository.findAll();
    }

    public void saveDefault() {
        this.shelfRepository.save(new Shelf(1, 1, 1));
    }

    //TEST
    public Shelf findById() {
        return this.shelfRepository.findById(1L).get();
    }
}
