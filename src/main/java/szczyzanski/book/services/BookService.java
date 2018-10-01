package szczyzanski.book.services;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import szczyzanski.book.api.dto.full.book.BookWithFullInfoDTO;
import szczyzanski.book.domain.entities.Author;
import szczyzanski.book.domain.entities.Book;
import szczyzanski.book.domain.entities.Tag;
import szczyzanski.book.domain.repositiories.BookRepository;
import szczyzanski.entities.builders.bn.catalog.parser.BNCatalogRecordParser;
import szczyzanski.exceptions.BNRecordParsingException;
import szczyzanski.external.services.HDDCoverHandler;
import szczyzanski.external.services.LCCoverDownloader;

import java.io.*;
import java.util.Collections;
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

    public Book findById(long id) {
        return bookRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public BookWithFullInfoDTO findOnBnCatalogByIsbn(long isbn) throws BNRecordParsingException {
        BNCatalogRecordParser recordParser = new BNCatalogRecordParser(isbn);
        BookWithFullInfoDTO bookWithFullInfoDTO = recordParser.findRecordByIsbn();
        LCCoverDownloader lcCoverDownloader = new LCCoverDownloader(bookWithFullInfoDTO);
        lcCoverDownloader.downloadCover();
        return bookWithFullInfoDTO;
    }

    public int getNoOfCovers(long id) {
        String pathname = "covers/" + id + "/";
        return new File(pathname).list().length;
    }

    public byte[] getCover(long id, int no) throws IOException {
        String pathname = "covers/" + id + "/" + no + ".jpg";
        File imageFile = new File(pathname);
        try (InputStream in = new FileInputStream(imageFile)) {
            return IOUtils.toByteArray(in);
        }
    }

    public byte[] getErrorCover(int errorCode) throws IOException {
        String pathname = "error/" + errorCode + ".jpg";
        File imageFile = new File(pathname);
        try (InputStream in = new FileInputStream(imageFile)) {
            return IOUtils.toByteArray(in);
        }
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    public void saveCovers(Book book) throws IOException {
        HDDCoverHandler.saveCovers(book.getId());
    }

    public void deleteCovers() throws IOException {
        HDDCoverHandler.clearCovers();
    }

    public List<Book> getLastNBooks(Long n) {
        List books = bookRepository.getLastNBooks(n);
        Collections.reverse(books);
        return books;
    }
}
