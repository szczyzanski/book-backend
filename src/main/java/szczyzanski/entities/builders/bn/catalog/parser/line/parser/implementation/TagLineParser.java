package szczyzanski.entities.builders.bn.catalog.parser.line.parser.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.LineParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagLineParser implements LineParser {
    final private static Logger logger = LoggerFactory.getLogger(TagLineParser.class);
    @Override
    public String parseLine(String line) throws MalformedLineException {
        checkArgument(line);
        Pattern pattern = Pattern.compile("\\p{L}+");
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            String tagStart = matcher.group();
            try {
                if(line.contains("|")) {
                    line = line.substring(0, line.indexOf("|"));
                }
                return line.substring(line.indexOf(tagStart)).trim().replaceAll(" +", " ");
            } catch (IndexOutOfBoundsException e) {
                final String MSG = "No tag found in line: " + line;
                MalformedLineException mle = new MalformedLineException(MSG, e);
                logger.error(MSG, mle);
                throw mle;
            }
        } else {
            final String MSG = "No tag found in line: " + line;
            MalformedLineException mle = new MalformedLineException(MSG);
            logger.error(MSG, mle);
            throw mle;
        }
    }
}
