package szczyzanski.book.api.controllers;
//TODO change from entities to DTOs
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import szczyzanski.book.api.dto.BookDTO;
import szczyzanski.book.domain.entities.Book;
import szczyzanski.book.services.AuthorService;
import szczyzanski.book.services.BookService;
import szczyzanski.book.services.ShelfService;

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
        List<BookDTO> result = new ArrayList<BookDTO>();
        for(Book book : bookList) {
            result.add(entityToDTO(book));
        }
        return result;
    }

    @RequestMapping(value = "/savedef")
    public void saveDefault() {
        bookService.saveDefault(shelfService.findById(1L), authorService.getDefaultSet());
    }

    private BookDTO entityToDTO(final Book book) {
        if(book == null) {
            return null;
        }
        return modelMapper.map(book, BookDTO.class);
    }

    @RequestMapping(value = "/isbn/{isbn}")
    public Book findByIsbn(@PathVariable long isbn) throws IOException {
        return bookService.findByIsbn(isbn);
    }
}
