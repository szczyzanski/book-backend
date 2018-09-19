package szczyzanski.book.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szczyzanski.book.domain.entities.Author;
import szczyzanski.book.domain.entities.Book;
import szczyzanski.book.domain.entities.Shelf;
import szczyzanski.book.domain.entities.Tag;
import szczyzanski.book.domain.repositiories.BookRepository;
import szczyzanski.entities.builders.bn.catalog.parser.BNCatalogRecordParser;
import szczyzanski.exceptions.BNRecordParsingException;
import szczyzanski.external.services.LCCoverDownloader;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BookService{
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private TagService tagService;

    public List<Book> findAll() {
        return (List) bookRepository.findAll();
    }

    public Book findOnBnCatalogByIsbn(long isbn) throws BNRecordParsingException {
        BNCatalogRecordParser recordParser = new BNCatalogRecordParser(isbn);
        Book book = recordParser.findRecordByIsbn();
        saveBook(book);
        updateAuthors(book);
        updateTags(book);
        LCCoverDownloader lcCoverDownloader = new LCCoverDownloader(book);
        lcCoverDownloader.downloadCover();
        return book;
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    private void updateAuthors(Book book) {
        Set<Author> foundAuthors = book.getAuthorSet();
        Set<Author> duplicatesOfAuthorsAlreadyInDb = new HashSet<>();
        Set<Author> authorsAlreadyInDb = new HashSet<>();
        for(Author author : foundAuthors) {
            String forname = author.getForname();
            String surname = author.getSurname();
            Author existingAuthor = authorService.findByName(forname, surname);
            if(existingAuthor == null) {
                author.setBookSet(new HashSet<>());
                author.getBookSet().add(book);
            } else {
                existingAuthor.getBookSet().add(book);
                duplicatesOfAuthorsAlreadyInDb.add(author);
                authorsAlreadyInDb.add(existingAuthor);
            }
        }
        book.getAuthorSet().removeAll(duplicatesOfAuthorsAlreadyInDb);
        book.getAuthorSet().addAll(authorsAlreadyInDb);
        Set<Author> updatedAuthors = book.getAuthorSet();
        for(Author author : updatedAuthors) {
            authorService.add(author);
        }
    }

    private void updateTags(Book book) {
        Set<Tag> foundTags = book.getTagSet();
        Set<Tag> duplicatesOfTagsAlreadInDb = new HashSet<>();
        Set<Tag> tagsAlreadyInDb = new HashSet<>();
        for(Tag tag : foundTags) {
            Tag exsistingTag = tagService.findByValue(tag.getValue());
            if(exsistingTag == null) {
                tag.setBookSet(new HashSet<>());
                tag.getBookSet().add(book);
            } else {
                exsistingTag.getBookSet().add(book);
                duplicatesOfTagsAlreadInDb.add(tag);
                tagsAlreadyInDb.add(exsistingTag);
            }
        }
        book.getTagSet().removeAll(duplicatesOfTagsAlreadInDb);
        book.getTagSet().addAll(tagsAlreadyInDb);
        Set<Tag> updatedTags = book.getTagSet();
        for(Tag tag : updatedTags) {
            tagService.add(tag);
        }
    }
}
