package szczyzanski.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szczyzanski.book.domain.entities.Author;
import szczyzanski.book.domain.entities.Book;
import szczyzanski.book.domain.entities.Shelf;
import szczyzanski.book.domain.repositiories.BookRepository;
import szczyzanski.entities.builders.bn.catalog.parser.BNCatalogRecordParser;
import szczyzanski.exceptions.BNRecordParsingException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookService{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;

    public List<Book> findAll() {
        return (List) bookRepository.findAll();
    }

    public Book findOnBnCatalogByIsbn(long isbn) throws BNRecordParsingException {
        BNCatalogRecordParser recordParser = new BNCatalogRecordParser();
        recordParser.setIsbn(isbn);
        Book book = recordParser.findRecordByIsbn();
        Set<Author> authors = book.getAuthorSet();
        book.getTagSet().stream().forEach(tag -> tag.getValue());
        saveBook(book);
        for(Author author : authors) {
            String forname = author.getForname();
            String surname = author.getSurname();
            Set<Author> existingAuthors = authorService.findByForname(forname, surname);
            if(existingAuthors.isEmpty()) {
                author.setBookSet(new HashSet<>());
                author.getBookSet().add(book);
                authorService.add(author);
            }
        }
        return book;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
}
