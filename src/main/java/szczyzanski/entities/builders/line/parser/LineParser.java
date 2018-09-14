package szczyzanski.entities.builders.line.parser;


import org.apache.commons.lang3.StringUtils;
import szczyzanski.exceptions.MalformedLineException;

public interface LineParser {
    String parseLine(String line) throws MalformedLineException;

    default void checkArgument(String line) {
        if(StringUtils.isBlank(line)) {
            throw new IllegalArgumentException("Argument for parsing is blank. In parser: " + this.getClass());
        }
    }
}
