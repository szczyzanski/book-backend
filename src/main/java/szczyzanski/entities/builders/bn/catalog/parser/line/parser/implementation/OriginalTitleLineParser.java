package szczyzanski.entities.builders.bn.catalog.parser.line.parser.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.LineParser;
import szczyzanski.exceptions.MalformedLineException;

public class OriginalTitleLineParser implements LineParser {
    final private static Logger logger = LoggerFactory.getLogger(OriginalTitleLineParser.class);
    @Override
    public String parseLine(String line) throws MalformedLineException {
        checkArgument(line);
        try {
            line = line.replaceAll("\\n|\\r", " ").replaceAll(" +", " ");
            line = line.substring(line.indexOf("Tytuł oryginału") + "Tytuł oryginału".length());
            if(line.indexOf(",|") >= 0) {
                line = line.substring(0, line.indexOf(",|"));
            } else {
                line = line.substring(0, line.indexOf(", |"));
            }
            line = line.replaceAll(":\\|a", "");
            line = line.replaceAll(" :\\|[a-z]", ": ");
            line = line.replaceAll("\n", " ").replaceAll("\r", "");
            line = line.trim().replaceAll(" +", " ");
            return  line;
        } catch (IndexOutOfBoundsException e) {
            final String MSG = "No original title found in line: " + line;
            MalformedLineException mle = new MalformedLineException(MSG);
            logger.error(MSG, mle);
            throw mle;
        }
    }
}
