package szczyzanski.entities.builders.bn.catalog.parser.utilities;

import org.apache.commons.lang3.StringUtils;
import szczyzanski.entities.builders.bn.catalog.parser.ParsingCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ProperLineFormer {
    private static List<String> workingList;

    public static List<String> formProperLines(String fileContent) {
        splitFileContentIntoLines(fileContent);
        joinLines();
        cutLinesOutOfRange();
        return workingList;
    }

    private static void splitFileContentIntoLines(String fileContent) {
        List<String> resultList = new ArrayList<>();
        Arrays.stream(fileContent.split(("\\r?\\n"))).forEach(line -> resultList.add(line));
        workingList = resultList;
    }

    private static void joinLines() {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < workingList.size(); i++) {
            if((i+1) < workingList.size()) {
                checkNextLine(i, workingList, result);
            } else {
                if(isCodeProper(workingList.get(i))) {
                    result.add(workingList.get(i));
                }
            }
        }
        workingList = result;
    }

    private static String getCode(String line) {
        int CODE_LENGTH = 3;
        if(line.length() > CODE_LENGTH)
            return line.substring(0, 3);
        else
            return "";
    }

    private static boolean isCodeProper(String line) {
        return StringUtils.isNumeric(getCode(line));
    }
    
    private static boolean isCodeInRange(String line) {
        List<String> inRangeCodes = ParsingCode.getCodes();
        for(String code : inRangeCodes) {
            if(getCode(line).equals(code)) {
                return true;
            }
        }
        return false;
    }

    private static void checkNextLine(int firstIndex, List<String> record, List<String> result) {
        String currentLine = record.get(firstIndex);
        String nextLine = record.get(firstIndex+1);
        if(isCodeProper(nextLine)) {
            result.add(currentLine);
        } else {
            while(!isCodeProper(nextLine)) {
                currentLine = currentLine + nextLine;
                record.remove(nextLine);
                nextLine = record.get(firstIndex+1);
            }
            result.add(currentLine);
        }
    }

    private static void cutLinesOutOfRange() {
        workingList = workingList.stream()
                .filter(line -> isCodeInRange(line))
                .collect(Collectors.toList());
    }
}
