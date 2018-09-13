package szczyzanski.utilities;


import org.apache.commons.lang3.StringUtils;
import szczyzanski.exceptions.NoSuchLineParserException;
import szczyzanski.utilities.implementation.*;
import szczyzanski.utilities.implementation.ISBNLineParser;
import szczyzanski.utilities.implementation.TitleLineParser;

import java.io.BufferedReader;

public class ParserFactory {
    public static LineParser chooseParser(String code, BufferedReader br) throws NoSuchLineParserException {
        if(StringUtils.isBlank(code)) {
            throw new IllegalArgumentException("code for factory is blank");
        }
        switch(code) {
            case "020":
                return new ISBNLineParser();
            case "100":
                return new NameLineParser();
            case "245":
               return new TitleLineParser();
            case "246":
                return new OriginalTitleLineParser();
            case "260":
                return new PublisherLineParser();
            case "300":
                return new NoOfPagesLineParser();
            case "380":
                return new TagLineParser();
            case "650":
                return new TagLineParser();
            case "651":
                return new TagLineParser();
            case "655":
                return new TagLineParser();
            case "658":
                return new TagLineParser();
            case "700":
                return new NameLineParser();
            default:
                throw new NoSuchLineParserException("LineParser not found - unhandled code number: " + code);
        }
    }
}
