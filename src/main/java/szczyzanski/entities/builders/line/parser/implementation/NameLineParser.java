package szczyzanski.entities.builders.line.parser.implementation;

import org.apache.commons.lang3.StringUtils;
import szczyzanski.entities.builders.line.parser.LineParser;
import szczyzanski.exceptions.MalformedLineException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameLineParser implements LineParser {
    @Override
    public String parseLine(String line) throws MalformedLineException {
        checkArgument(line);
        //regex for surname
        line = line.replaceAll("\\d|\\s", "");
        Pattern pattern = Pattern.compile("\\D*,");
        Matcher matcher = pattern.matcher(line);
        String surname;
        if(matcher.find()) {
            surname = matcher.group();
        } else {
            throw new MalformedLineException("Surname not found in line: " + line);
        }
        //regex for firstname
        try {
            line = line.substring(line.indexOf(surname) + surname.length());
            line = line.substring(0, line.indexOf("|"));
            //multiple names - split
            String[] tab = line.split("\\p{Lu}");
            String firstname = "";
            for(String name : tab) {
                int capitalLetterIndex = line.indexOf(name) - 1;
                if(!StringUtils.isBlank(name) &&  capitalLetterIndex >= 0) {
                    char capitalLetter = line.charAt(capitalLetterIndex);
                    firstname += capitalLetter + name;
                    if(firstname.length() > 2 && firstname.endsWith(".")) {
                        firstname = firstname.substring(0, firstname.length() - 1);
                    }
                    firstname += " ";
                    line = line.substring(capitalLetterIndex + name.length() + 1);
                }
            }
            //result
            return firstname + surname.substring(0, surname.length()-1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new MalformedLineException("No name found in line (problem with String.length): " + line);
        }
    }
}
