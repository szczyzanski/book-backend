package szczyzanski.entities.builders.line.parser.implementation;

import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.entities.builders.line.parser.LineParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagLineParser implements LineParser {
    @Override
    public String parseLine(String line) throws MalformedLineException {
        checkArgument(line);
        Pattern pattern = Pattern.compile("\\p{L}+");
        Matcher matcher = pattern.matcher(line);
        if(matcher.find()) {
            String tagStart = matcher.group();
            try {
                return line.substring(line.indexOf(tagStart)).trim().replaceAll(" +", " ");
            } catch (IndexOutOfBoundsException e) {
                throw new MalformedLineException("No tag found in line: " + line);
            }
        } else {
            throw new MalformedLineException("No tag found in line: " + line);
        }
    }
}
