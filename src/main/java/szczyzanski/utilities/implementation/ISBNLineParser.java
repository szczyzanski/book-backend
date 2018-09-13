package szczyzanski.utilities.implementation;

import org.apache.commons.lang3.StringUtils;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.utilities.LineParser;

public class ISBNLineParser implements LineParser {
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
                throw new MalformedLineException("No ISBN number found in line (not numeric): " + lineWithoutCodeAndSpaces);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new MalformedLineException("No ISBN number found in line (too short): " + line);
        }
    }
}
