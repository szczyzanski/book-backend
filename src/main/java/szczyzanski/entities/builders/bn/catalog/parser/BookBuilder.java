package szczyzanski.entities.builders.bn.catalog.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import szczyzanski.book.domain.entities.Book;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.entities.builders.bn.catalog.parser.utilities.ProperLineFormer;
import szczyzanski.entities.builders.bn.catalog.parser.utilities.PropertyController;
import szczyzanski.entities.builders.bn.catalog.parser.utilities.PropertyControllerFactory;

import java.util.List;

public class BookBuilder {
    final private static Logger logger = LoggerFactory.getLogger(BookBuilder.class);
    public Book createBookFromFile(String fileContent) {
        List<String> list = ProperLineFormer.formProperLines(fileContent);
        Book book = new Book();
        for(String line : list) {
            try {
                PropertyController propertyController = PropertyControllerFactory.chooseProperty(getCode(line));
                propertyController.changeProperty(book, line);
            } catch (MalformedLineException e) {
                logger.error("Not proper line: " + line, e);
            }
        }
        return book;
    }

    private String getCode(String line) {
        return line.substring(0, 3);
    }
}
