package szczyzanski.entities.builders.bn.catalog.parser.utilities;

import szczyzanski.book.domain.entities.Book;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.LineParser;
import szczyzanski.entities.builders.bn.catalog.parser.property.setter.PropertySetter;

public class PropertyController {
    private LineParser lineParser;
    private PropertySetter propertySetter;

    public PropertyController(LineParser lineParser, PropertySetter propertySetter) {
        this.propertySetter = propertySetter;
        this.lineParser = lineParser;
    }

    public void changeProperty(Book book, String line) throws MalformedLineException {
        String value = lineParser.parseLine(line);
        propertySetter.setProperty(value, book);
    }
}
