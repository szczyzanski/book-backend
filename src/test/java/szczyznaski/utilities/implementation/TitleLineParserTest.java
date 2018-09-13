package szczyznaski.utilities.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.utilities.LineParser;
import szczyzanski.utilities.implementation.TitleLineParser;

import java.util.concurrent.atomic.AtomicInteger;

public class TitleLineParserTest {
    @Test
    public void shouldReturnTitle() throws MalformedLineException {
        //given
        String[] lines = {"245 10 Ze świętej góry :|bna ścieżkach chrześcijańskiego " +
                "Bizancjum /|cWilliam Dalrymple ; przełożył Krzysztof Obłucki.}",
                "245 10 Inni ludzie /|cDorota Masłowska ; ilustracje Maciej Chorąży.",
                "245 10 Muzeum niewinności /|cOrhan Pamuk ; przełożyła Anna Akbike\n" +
                        "       Sulimowicz.",
                "245 10 Zbiornik 13 /|cJon McGregor ; przełożyła Jolanta Kozak. ",
                "245 10 Czekoladki dla prezesa /|cSławomir Mrożek. ",
                "245 10 Poświata /|cMichael Chabon ; przełożył Michał Kłobukowski.",
                "245 10 Żywego ducha /|cJerzy Pilch. "};
        String[] expectedResults = {"Ze świętej góry: na ścieżkach chrześcijańskiego Bizancjum",
                                    "Inni ludzie",
                                    "Muzeum niewinności",
                                    "Zbiornik 13",
                                    "Czekoladki dla prezesa",
                                    "Poświata",
                                    "Żywego ducha"};
        String[] actualResults = new String[lines.length];
        LineParser titleLineParser = new TitleLineParser();
        final int TESTS_NO = lines.length;
        //when
        for(int i = 0; i < TESTS_NO; i++) {
            actualResults[i] = titleLineParser.parseLine(lines[i]);
        }
        //then
        for(int i = 0; i < TESTS_NO; i++) {
            Assertions.assertEquals(expectedResults[i], actualResults[i]);
        }
    }
    @Test
    public void shouldThrowIllegalArgumentException() {
        //given
        String[] lines = {"",
                null};
        LineParser titleLineParser = new TitleLineParser();
        final int TESTS_NO = lines.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> titleLineParser.parseLine(lines[index.get()]));
        }
    }
    @Test
    public void shouldThrowMalformedLineException() {
        //given
        String[] lines = {"abc",
                "dsadasdsaczxcasdasdsadas",
                "31290dsa123930123129"};
        LineParser titleLineParser = new TitleLineParser();
        final int TESTS_NO = lines.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(MalformedLineException.class,
                    () -> titleLineParser.parseLine(lines[index.get()]));
        }
    }
}
