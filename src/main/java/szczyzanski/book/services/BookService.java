package szczyzanski.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szczyzanski.book.domain.entities.Book;
import szczyzanski.book.domain.repositiories.BookRepository;

import java.util.List;

@Service
public class BookService{
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return (List) bookRepository.findAll();
    }

    public void saveDefault() {
        bookRepository.save(new Book("Barti", 1L));
    }
}
