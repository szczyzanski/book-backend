package szczyzanski.entities.builders.line.parser.implementation;

import szczyzanski.entities.builders.line.parser.LineParser;
import szczyzanski.exceptions.MalformedLineException;

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
