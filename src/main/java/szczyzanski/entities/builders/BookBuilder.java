package szczyzanski.entities.builders;

import szczyzanski.book.domain.entities.Book;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.entities.builders.utilities.ProperLineFormer;
import szczyzanski.entities.builders.utilities.PropertyController;
import szczyzanski.entities.builders.utilities.PropertyControllerFactory;

import java.util.List;

public class BookBuilder {
    public Book createBookFromFile(String fileContent) {
        List<String> list = ProperLineFormer.formProperLines(fileContent);
        Book book = new Book();
        for(String line : list) {
            try {
                PropertyController propertyController = PropertyControllerFactory.chooseProperty(getCode(line));
                propertyController.changeProperty(book, line);
            } catch (MalformedLineException e) {
                e.printStackTrace();
            }
        }
        return book;
    }

    private String getCode(String line) {
        return line.substring(0, 3);
    }
}
