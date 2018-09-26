package szczyzanski.entities.builders.bn.catalog.parser.utilities;

import szczyzanski.book.api.dto.full.book.BookWithFullInfoDTO;
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

    public void changeProperty(BookWithFullInfoDTO bookWithFullInfoDTO, String line) throws MalformedLineException {
        String value = lineParser.parseLine(line);
        propertySetter.setProperty(value, bookWithFullInfoDTO);
    }
}
