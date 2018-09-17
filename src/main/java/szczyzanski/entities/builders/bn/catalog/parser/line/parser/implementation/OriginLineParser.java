package szczyzanski.entities.builders.bn.catalog.parser.line.parser.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.LineParser;

public class OriginLineParser implements LineParser {
    final private static Logger logger = LoggerFactory.getLogger(OriginLineParser.class);
    @Override
    public String parseLine(String line) throws MalformedLineException {
        checkArgument(line);
        try {
            return line.substring(line.indexOf("Przynależność kulturowa")
                    + "Przynależność kulturowa".length())
                    .replaceAll("\\|[a-z]", "").trim().replaceAll(" +", " ");
        } catch (IndexOutOfBoundsException e) {
            final String MSG = "No origin found in line: " + line;
            MalformedLineException mle = new MalformedLineException(MSG);
            logger.error(MSG, mle);
            throw mle;
        }

    }
}
