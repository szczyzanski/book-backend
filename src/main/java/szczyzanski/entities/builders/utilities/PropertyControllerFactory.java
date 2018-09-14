package szczyzanski.entities.builders.utilities;

import org.apache.commons.lang3.StringUtils;
import szczyzanski.entities.builders.ParsingCode;
import szczyzanski.entities.builders.line.parser.implementation.*;
import szczyzanski.entities.builders.property.setter.implementation.*;

public class PropertyControllerFactory {
    public static PropertyController chooseProperty(String code) {
        if(StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("code for factory is blank");
        }
        ParsingCode parsingCode = convertStringIntoParsingCode(code);
        if(parsingCode == null) {
            throw new IllegalArgumentException("code for factory not found: " + code);
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
