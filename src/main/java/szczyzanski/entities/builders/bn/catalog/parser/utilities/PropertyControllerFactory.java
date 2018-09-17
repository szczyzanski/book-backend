package szczyzanski.entities.builders.bn.catalog.parser.utilities;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import szczyzanski.entities.builders.bn.catalog.parser.ParsingCode;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.implementation.*;
import szczyzanski.entities.builders.bn.catalog.parser.property.setter.implementation.*;

public class PropertyControllerFactory {
    final private static Logger logger = LoggerFactory.getLogger(PropertyControllerFactory.class);
    public static PropertyController chooseProperty(String code) {
        if(StringUtils.isBlank(code)) {
            final String MSG = "code for factory is blank";
            IllegalArgumentException iae = new IllegalArgumentException(MSG);
            logger.error(MSG, iae);
            throw iae;
        }
        ParsingCode parsingCode = convertStringIntoParsingCode(code);
        if(parsingCode == null) {
            final String MSG = "code for factory not found: " + code;
            IllegalArgumentException iae = new IllegalArgumentException(MSG);
            logger.error(MSG, iae);
            throw iae;
        }
        switch(parsingCode) {
            case ISBN:
                return new PropertyController(new ISBNLineParser(), new ISBNPropertySetter());
            case NAME_1:
                return new PropertyController(new NameLineParser(), new NamePropertySetter());
            case TITLE:
                return new PropertyController(new TitleLineParser(), new TitlePropertySetter());
            case ORIGINAL_TITLE:
                return new PropertyController(new OriginalTitleLineParser(), new OriginalTitlePropertySetter());
            case PUBLISHER:
                return new PropertyController(new PublisherLineParser(), new PublisherPropertySetter());
            case NUMBER_OF_PAGES:
                return new PropertyController(new NoOfPagesLineParser(), new NoOfPagesPropertySetter());
            case TAG_1:
                return new PropertyController(new TagLineParser(), new TagPropertySetter());
            case ORIGIN:
                return new PropertyController(new OriginLineParser(), new OriginPropertySetter());
            case TAG_2:
                return new PropertyController(new TagLineParser(), new TagPropertySetter());
            case TAG_3:
                return new PropertyController(new TagLineParser(), new TagPropertySetter());
            case TAG_4:
                return new PropertyController(new TagLineParser(), new TagPropertySetter());
            case TAG_5:
                return new PropertyController(new TagLineParser(), new TagPropertySetter());
            case NAME_2:
                return new PropertyController(new NameLineParser(), new NamePropertySetter());
            default:
                return null;
        }
    }
    private static ParsingCode convertStringIntoParsingCode(String code) {
        for(ParsingCode parsingCode : ParsingCode.values()) {
            if(code.equals(parsingCode.getValue())) {
                return parsingCode;
            }
        }
        return null;
    }
}
