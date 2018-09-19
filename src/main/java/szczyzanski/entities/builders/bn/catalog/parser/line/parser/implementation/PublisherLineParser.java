package szczyzanski.entities.builders.bn.catalog.parser.line.parser.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.LineParser;

public class PublisherLineParser implements LineParser {
    final private static Logger logger = LoggerFactory.getLogger(PublisherLineParser.class);
    @Override
    public String parseLine(String line) throws MalformedLineException {
        checkArgument(line);
        try {
            line = line.replaceAll("\\n|\\r", " ").replaceAll(" +", " ");
            line = line.substring(line.indexOf(":|"));
            if(line.indexOf(",|") >= 0) {
                line = line.substring(0, line.indexOf(",|"));
            } else {
                line = line.substring(0, line.indexOf(", |"));
            }
            line = line.replaceAll(":\\|[a-z]", "");
            return line;
        } catch (IndexOutOfBoundsException e) {
            final String MSG = "No publisher found in line: " + line;
            MalformedLineException mle = new MalformedLineException(MSG);
            logger.error(MSG, mle);
            throw mle;
        }
    }
}
