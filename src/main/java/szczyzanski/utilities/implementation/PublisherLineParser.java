package szczyzanski.utilities.implementation;

import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.utilities.LineParser;

public class PublisherLineParser implements LineParser {
    @Override
    public String parseLine(String line) throws MalformedLineException {
        checkArgument(line);
        try {
            line = line.substring(line.indexOf(":|"));
            line = line.substring(0, line.indexOf(",|"));
            line = line.replaceAll(":\\|[a-z]", "");
            return line;
        } catch (IndexOutOfBoundsException e) {
            throw new MalformedLineException("No publisher found in line: " + line);
        }
    }
}
