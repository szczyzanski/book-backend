package szczyzanski.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szczyzanski.book.domain.entities.Author;
import szczyzanski.book.domain.entities.Book;
import szczyzanski.book.domain.entities.Shelf;
import szczyzanski.book.domain.repositiories.BookRepository;
import szczyzanski.utilities.BnParser;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

@Service
public class BookService{
    @Autowired
    private BookRepository bookRepository;

    public List<Book> findAll() {
        return (List) bookRepository.findAll();
    }

    public void saveDefault(Shelf shelf, Set<Author> authorSet) {
        Book book = new Book("Barti", shelf, authorSet);
        shelf.addBook(book);
        for(Author author : authorSet) {
            author.addBook(book);
        }
        bookRepository.save(book);
    }

   public Book findByIsbn(long isbn) throws IOException {
       return BnParser.findIsbn(isbn);
   }
}
