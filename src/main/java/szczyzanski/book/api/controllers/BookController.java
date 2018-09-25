package szczyzanski.book.api.controllers;
//TODO change from entities to DTOs
import org.json.JSONObject;
import org.modelmapper.ModelMapper;
import org.slf4j.profiler.Profiler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import szczyzanski.book.api.dto.BookDTO;
import szczyzanski.book.domain.entities.Book;
import szczyzanski.book.services.AuthorService;
import szczyzanski.book.services.BookService;
import szczyzanski.book.services.ShelfService;
import szczyzanski.exceptions.BNRecordParsingException;
import szczyzanski.external.services.SuggestedTagGenerator;

import java.io.FileNotFoundException;
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
    @CrossOrigin(origins = "http://localhost:4200")
    public List<BookDTO> findAll() {
        List<Book> bookList = bookService.findAll();
        List<BookDTO> result = new ArrayList<>();
        for(Book book : bookList) {
            result.add(entityToDTO(book));
        }
        return result;
    }

    @RequestMapping(value = "/isbn/{isbn}")
    public BookDTO findByIsbn(@PathVariable long isbn) throws BNRecordParsingException, FileNotFoundException {
        Book book = bookService.findOnBnCatalogByIsbn(isbn);
        int repoSize = bookService.findAll().size();
        SuggestedTagGenerator stg = new SuggestedTagGenerator(book, repoSize);
        Profiler profiler = new Profiler("tag generator");
        profiler.start("tag print");
        stg.printSuggestedTags();
        System.out.println("***********************************************************************************");
        profiler.stop().print();
        System.out.println("***********************************************************************************");
        return entityToDTO(book);
    }

    @RequestMapping(value = "/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public BookDTO findById(@PathVariable long id) {
        return entityToDTO(bookService.findById(id));
    }

    @RequestMapping(value = "/cover/n/{id}")
    @CrossOrigin(origins = "http://localhost:4200")
    public int getNoOfCovers(@PathVariable long id) {
        return bookService.getNoOfCovers(id);
    }

    @GetMapping(
            value = "/cover/{id}&{no}",
            produces = MediaType.IMAGE_JPEG_VALUE
    )
    @CrossOrigin(origins = "http://localhost:4200")
    public byte[] getCover(@PathVariable final long id, @PathVariable final int no) throws IOException {
        return bookService.getCover(id, no);
    }

    private BookDTO entityToDTO(final Book book) {
        if(book == null) {
            return null;
        }
        return modelMapper.map(book, BookDTO.class);
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
                9788380495517L,
                9788380495647L,
                9788328044340L,
                9788365613257L,
                9788380622203L,
                9788365780607L,
                9788380621497L,
                9788365613516L,
                9788380620506L,
                9788328045965L,
                9788380495579L,
                9788308063590L,
                9788308063613L,
                9788380495395L,
                9788324049851L,
                9788308063712L,
                9788378187127L,
                9788328044791L,
                9788364384684L,
                9788374808279L,
                9788306033946L,
                9788374808286L,
                9788308048900L,
                9788380495005L,
                9788365676924L,
                9788328044357L,
                9788365534491L,
                9788328703995L,
                9788380321694L,
                9788374807296L,
                9788380494947L,
                9788380621985L,
                9788365613233L,
                9788380970809L,
                9788365781017L,
                9788308063514L,
                9788308062388L,
                9788364648526L,
                9788374806596L,
                9788307034126L,
                9788365613073L,
                9788328036963L,
                9788380621626L,
                9788380321717L,
                9788364384646L,
                9788380970847L,
                9788326824791L,
                9788377186046L,
                9788365271402L,
                9788377186053L,
                9788380494619L,
                9788307033853L,
                9788364887505L,
                9788380493759L,
                9788380493759L,
                9788379431090L,
                9788380493575L,
                9788377186039L,
                9788379247752L,
                9788324227440L,
                9788364384592L,
                9788379858897L,
                9788380620490L,
                9788326823961L,
                9788380316294L,
                9788373926127L,
                9788380493520L,
                9788380970335L,
                9788380620964L,
                9788380494282L,
                9788380621282L,
                9788380494305L,
                9788364887307L,
                9788328026018L,
                9788380970243L,
                9788365676115L,
                9788364887406L,
                9788364822643L,
                9788380321304L,
                9788324230334L,
                9788379246984L,
                9788328026438L,
                9788377312469L,
                9788328027411L,
                9788364822636L,
                9788380321243L,
                9788308061930L,
                9788364384585L,
                9788324230563L,
                9788308062241L,
                9788328021518L,
                9788380620452L,
                9788379950690L,
                9788328703964L,
                9788378931133L,
                9788379247011L,
                9788380694767L,
                9788328026179L,
                9788378931577L,
                9788370541651L,
                9788308062210L,
                9788377589328L,
                9788328021938L,
                9788324026524L,
                9788364822506L,
                9788377186022L,
                9788380310827L,
                9788364384530L,
                9788380693920L,
                9788379246366L,
                9788380693487L,
                9788380493179L,
                9788380620100L,
                9788377186015L,
                9788377792926L,
                9788364030734L,
                9788365271129L,
                9788375082678L,
                9788364030857L,
                9788374806527L,
                9788378188780L,
                9788328020986L,
                9788374806497L,
                9788374806497L,
                9788379990399L,
                9788379246861L,
                9788380490246L,
                9788374806411L,
                9788306031546L,
                9788374806381L,
                9788376422268L,
                9788365315366L,
                9788379641703L,
                9788379245185L,
                9788374806343L,
                9788306031157L,
                9788365282330L,
                9788374806442L,
                9788374806398L,
                9788364822469L,
                9788365157003L,
                9788364297694L,
                9788374806312L,
                9788380492196L,
                9788378187431L,
                9788380492561L,
                9788364648199L
        };
        Profiler profiler = new Profiler("basic");
        profiler.start("work");
        for(long isbn : testArgumentList) {
            bookService.findOnBnCatalogByIsbn(isbn);
        }
        /*for(int i = 0; i < 10; i++) {
            bookService.findOnBnCatalogByIsbn(testArgumentList[i]);
        }*/
        System.out.println("***********************************************************************************");
        profiler.stop().print();
        System.out.println("***********************************************************************************");
    }
}
