package szczyzanski.entities.builders.bn.catalog.parser.line.parser.implementation;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.LineParser;

public class ISBNLineParser implements LineParser {
    final private static Logger logger = LoggerFactory.getLogger(ISBNLineParser.class);
    @Override
    public String parseLine(String line) throws MalformedLineException {
        checkArgument(line);
        final int ISBN_LENGTH = 13;
        try {
            String lineWithoutCodeAndSpaces = line.substring(4).replaceAll("\\s", "");
            lineWithoutCodeAndSpaces = lineWithoutCodeAndSpaces.substring(0, ISBN_LENGTH);
            if(StringUtils.isNumeric(lineWithoutCodeAndSpaces)) {
                return lineWithoutCodeAndSpaces;
            } else {
                final String MSG = "No ISBN number found in line (not numeric): " + lineWithoutCodeAndSpaces;
                MalformedLineException mle = new MalformedLineException(MSG);
                logger.error(MSG, mle);
                throw mle;
            }
        } catch (StringIndexOutOfBoundsException e) {
            final String MSG = "No ISBN number found in line (too short): " + line;
            MalformedLineException mle = new MalformedLineException(MSG, e);
            logger.error(MSG, mle);
            throw mle;
        }
    }
}
