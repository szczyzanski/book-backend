package szczyzanski.entities.builders.line.parser.implementation;

import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.entities.builders.line.parser.LineParser;

public class OriginLineParser implements LineParser {
    @Override
    public String parseLine(String line) throws MalformedLineException {
        checkArgument(line);
        try {
            return line.substring(line.indexOf("Przynależność kulturowa")
                    + "Przynależność kulturowa".length())
                    .replaceAll("\\|[a-z]", "").trim().replaceAll(" +", " ");
        } catch (IndexOutOfBoundsException e) {
            throw new MalformedLineException("No origin found in line: " + line);
        }

    }
}
