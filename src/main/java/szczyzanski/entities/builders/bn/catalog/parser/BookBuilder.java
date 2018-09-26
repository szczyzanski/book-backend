package szczyzanski.entities.builders.bn.catalog.parser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import szczyzanski.book.api.dto.full.book.BookWithFullInfoDTO;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.entities.builders.bn.catalog.parser.utilities.ProperLineFormer;
import szczyzanski.entities.builders.bn.catalog.parser.utilities.PropertyController;
import szczyzanski.entities.builders.bn.catalog.parser.utilities.PropertyControllerFactory;

import java.util.List;

public class BookBuilder {
    final private static Logger logger = LoggerFactory.getLogger(BookBuilder.class);
    public BookWithFullInfoDTO createBookFromFile(String fileContent) {
        List<String> list = ProperLineFormer.formProperLines(fileContent);
        BookWithFullInfoDTO bookWithFullInfoDTO = new BookWithFullInfoDTO();
        for(String line : list) {
            try {
                PropertyController propertyController = PropertyControllerFactory.chooseProperty(getCode(line));
                propertyController.changeProperty(bookWithFullInfoDTO, line);
            } catch (MalformedLineException e) {
                logger.error("Not proper line: " + line, e);
            }
        }
        return bookWithFullInfoDTO;
    }

    private String getCode(String line) {
        return line.substring(0, 3);
    }
}
