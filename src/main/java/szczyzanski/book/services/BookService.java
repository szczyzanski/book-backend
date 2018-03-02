package szczyzanski.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szczyzanski.book.domain.entities.Book;
import szczyzanski.book.domain.repositiories.BookRepository;

import java.util.ArrayList;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public ArrayList<Book> findAll() {
        return (ArrayList) bookRepository.findAll();
    }
}
