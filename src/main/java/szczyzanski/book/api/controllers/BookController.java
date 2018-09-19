package szczyzanski.book.api.controllers;
//TODO change from entities to DTOs
import org.modelmapper.ModelMapper;
import org.slf4j.profiler.Profiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import szczyzanski.book.api.dto.BookDTO;
import szczyzanski.book.domain.entities.Book;
import szczyzanski.book.services.AuthorService;
import szczyzanski.book.services.BookService;
import szczyzanski.book.services.ShelfService;
import szczyzanski.exceptions.BNRecordParsingException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private ShelfService shelfService;
    @Autowired
    private AuthorService authorService;
    @Autowired
    private ModelMapper modelMapper;

    @RequestMapping(value = "/all")
    public List<BookDTO> findAll() {
        List<Book> bookList = bookService.findAll();
        List<BookDTO> result = new ArrayList<>();
        for(Book book : bookList) {
            result.add(entityToDTO(book));
        }
        return result;
    }

    @RequestMapping(value = "/isbn/{isbn}")
    public BookDTO findByIsbn(@PathVariable long isbn) throws BNRecordParsingException {
        Book book = bookService.findOnBnCatalogByIsbn(isbn);
        return entityToDTO(book);
    }

    @RequestMapping(value = "/test")
    public void testMethod() throws BNRecordParsingException {
        long[] testArgumentList = {
                9788365613059L,
                9788308065105L,
                9788365613608L,
                9788328053571L,
                9788308065228L,
                9788365613622L,
                9788307034164L,
                9788380496712L,
                9788374809030L,
                9788306034073L,
                9788365613561L,
                9788308064955L,
                9788381231947L,
                9788308064764L,
                9788324053360L,
                9788380622845L,
                9788365613417L,
                9788306034257L,
                9788380496279L,
                9788324053322L,
                9788306034271L,
                9788328048171L,
                9788365125675L,
                9788365973047L,
                9788328050631L,
                9788364858796L,
                9788394725488L,
                9788365271358L,
                9788328046306L,
                9788308064245L,
                9788308064467L,
                9788308063118L,
                9788365271587L,
                9788308063910L,
                9788380622555L,
                9788381101509L,
                9788365613158L,
                9788380315877L,
                9788365271570L,
                9788364887376L,
                9788328705463L,
                9788365836274L,
                9788377312766L,
                9788328037298L,
                9788308064177L,
                9788374808910L,
                9788365780706L,
                9788381230803L,
                9788379822690L,
                9788364384707L,
                9788380321922L,
                9788365836366L,
                9788380621749L,
                9788328045880L,
                9788365836397L,
                9788328047433L,
                9788308063507L,
                9788365613219L,
                9788308063989L,
                9788380321540L,
                9788365781109L,
                9788380495654L,
                9788306033991L,
        };
        Profiler profiler = new Profiler("basic");
        profiler.start("work");
        for(long isbn : testArgumentList) {
            bookService.findOnBnCatalogByIsbn(isbn);
        }
        System.out.println("***********************************************************************************");
        profiler.stop().print();
        System.out.println("***********************************************************************************");
    }

    private BookDTO entityToDTO(final Book book) {
        if(book == null) {
            return null;
        }
        return modelMapper.map(book, BookDTO.class);
    }
}
