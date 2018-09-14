package szczyzanski.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szczyzanski.book.domain.entities.Author;
import szczyzanski.book.domain.entities.Book;
import szczyzanski.book.domain.entities.Shelf;
import szczyzanski.book.domain.repositiories.BookRepository;
import szczyzanski.entities.builders.BNCatalogRecordParser;
import szczyzanski.exceptions.BNRecordParsingException;

import java.util.List;
import java.util.Set;

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

    public Book findOnBnCatalogByIsbn(long isbn) throws BNRecordParsingException {
        BNCatalogRecordParser recordParser = new BNCatalogRecordParser();
        recordParser.setIsbn(isbn);
        return recordParser.findRecordByIsbn();
    }
}
