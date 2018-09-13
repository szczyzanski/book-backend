package szczyzanski.utilities.implementation;

import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.utilities.LineParser;

public class TitleLineParser implements LineParser {
    @Override
    public String parseLine(String line) throws MalformedLineException {
        checkArgument(line);
        String lineWithoutCode = line.replace("245 10 ", "");
        try {
            lineWithoutCode = lineWithoutCode.substring(0, lineWithoutCode.indexOf("/"));
            lineWithoutCode = lineWithoutCode.replaceAll("\\|.*\\|\\p{L}", " ");
            lineWithoutCode = lineWithoutCode.replaceAll("\\|b", "");
            lineWithoutCode = lineWithoutCode.trim().replaceAll(" +", " ");
            return lineWithoutCode.replaceAll(" :", ": ");
        } catch (StringIndexOutOfBoundsException e) {
            throw new MalformedLineException("No title found in line: " + line);
        }
    }
}
