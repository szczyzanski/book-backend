package szczyzanski.entities.builders;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public enum ParsingCode {
    ISBN("020"),
    NAME_1("100"),
    TITLE("245"),
    ORIGINAL_TITLE("246"),
    PUBLISHER("260"),
    NUMBER_OF_PAGES("300"),
    TAG_1("380"),
    ORIGIN("386"),
    TAG_2("650"),
    TAG_3("651"),
    TAG_4("655"),
    TAG_5("658"),
    NAME_2("700");

    private final String code;

    ParsingCode(final String code) {
        this.code = code;
    }

    public final String getValue() {
        return code;
    }

    public static List<String> getCodes() {
        List<String> list = new ArrayList<>();
        EnumSet.allOf(ParsingCode.class)
                .forEach(parsingCode -> list.add(parsingCode.getValue()));
        return list;
    }
}
