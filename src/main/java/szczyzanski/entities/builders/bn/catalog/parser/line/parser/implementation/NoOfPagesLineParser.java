package szczyzanski.entities.builders.bn.catalog.parser.line.parser.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.LineParser;
import szczyzanski.exceptions.MalformedLineException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoOfPagesLineParser implements LineParser {
    final private static Logger logger = LoggerFactory.getLogger(NoOfPagesLineParser.class);
    @Override
    public String parseLine(String line) throws MalformedLineException {
        checkArgument(line);
        Pattern pattern = Pattern.compile("\\d+");
        try {
            String lineWithoutCode = line.substring(4);
            Matcher matcher = pattern.matcher(lineWithoutCode);
            if(matcher.find()) {
                return matcher.group();
            } else {
                final String MSG = "No number of pages found in line: " + line;
                MalformedLineException mle = new MalformedLineException(MSG);
                logger.error(MSG, mle);
                throw mle;
            }
        } catch (StringIndexOutOfBoundsException e) {
            final String MSG = "No number of pages found in line (too short): " + line;
            MalformedLineException mle = new MalformedLineException(MSG, e);
            logger.error(MSG, mle);
            throw mle;
        }
    }
}
