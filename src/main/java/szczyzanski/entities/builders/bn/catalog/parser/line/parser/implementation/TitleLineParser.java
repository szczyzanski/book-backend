package szczyzanski.entities.builders.bn.catalog.parser.line.parser.implementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.LineParser;
import szczyzanski.exceptions.MalformedLineException;

public class TitleLineParser implements LineParser {
    final private static Logger logger = LoggerFactory.getLogger(TitleLineParser.class);
    @Override
    public String parseLine(String line) throws MalformedLineException {
        checkArgument(line);
        String lineWithoutCode = line.replace("245 10 ", "");
        try {
            lineWithoutCode = lineWithoutCode.substring(0, lineWithoutCode.indexOf("/"));
            lineWithoutCode = lineWithoutCode.replaceAll("\\|.*\\|\\p{L}", " ");
            lineWithoutCode = lineWithoutCode.replaceAll("\\|\\p{L}", " ");
            lineWithoutCode = lineWithoutCode.replaceAll("\\|b", "");
            lineWithoutCode = lineWithoutCode.replaceAll(" :", ": ");
            //IF FOR 9788308064177 (SERCE CIEMNOSCI)
            if(lineWithoutCode.contains("=")) {
                lineWithoutCode = lineWithoutCode.substring(0, lineWithoutCode.indexOf("="));
            }
            //
            lineWithoutCode = lineWithoutCode.trim().replaceAll(" +", " ");
            return lineWithoutCode;
        } catch (StringIndexOutOfBoundsException e) {
            final String MSG = "No title found in line: " + line;
            MalformedLineException mle = new MalformedLineException(MSG);
            logger.error(MSG, mle);
            throw mle;
        }
    }
}
