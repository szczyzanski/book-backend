package szczyzanski.utilities.implementation;

import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.utilities.LineParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NoOfPagesLineParser implements LineParser {
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
                throw new MalformedLineException("No number of pages found in line: " + line);
            }
        } catch (StringIndexOutOfBoundsException e) {
            throw new MalformedLineException("No number of pages found in line (too short): " + line);
        }
    }
}
