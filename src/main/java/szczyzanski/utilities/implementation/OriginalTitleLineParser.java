package szczyzanski.utilities.implementation;

import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.utilities.LineParser;

public class OriginalTitleLineParser implements LineParser {
    @Override
    public String parseLine(String line) throws MalformedLineException {
        checkArgument(line);
        try {
            line = line.substring(line.indexOf("Tytuł oryginału") + "Tytuł oryginału".length());
            line = line.substring(0, line.indexOf(",|"));
            line = line.replaceAll(":\\|a", "");
            line = line.replaceAll(" :\\|[a-z]", ": ");
            line = line.replaceAll("\n", " ").replaceAll("\r", "");
            line = line.trim().replaceAll(" +", " ");
            return  line;
        } catch (IndexOutOfBoundsException e) {
            throw new MalformedLineException("No original title found in line: " + line);
        }
    }
}
