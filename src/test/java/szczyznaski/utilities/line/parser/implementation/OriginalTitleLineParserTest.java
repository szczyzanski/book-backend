package szczyznaski.utilities.line.parser.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.LineParser;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.implementation.OriginalTitleLineParser;

import java.util.concurrent.atomic.AtomicInteger;

public class OriginalTitleLineParserTest {
    @Test
    public void shouldReturnOriginalTitle() throws MalformedLineException {
        //given
        String[] lines = {"246 1  |iTytuł oryginału:|aMoonglow,|f2016 ",
                            "246 1  |iTytuł oryginału:|aReservoir 13,|f2017 ",
                            "246 1  |iTytuł oryginału:|aMasumiyet Müzesi,|f2008 ",
                            "246 1  |iTytuł oryginału:|aFrom the holy mountain :|ba journey \n" +
                                    "       among the Christians of the Middle East,|f1997 "};
        String[] expectedResults = {"Moonglow",
                                    "Reservoir 13",
                                    "Masumiyet Müzesi",
                                    "From the holy mountain: a journey among the Christians of the Middle East"};
        String[] actualResults = new String[lines.length];
        LineParser originalTitleLineParser = new OriginalTitleLineParser();
        final int TESTS_NO = lines.length;
        //when
        for(int i = 0; i < TESTS_NO; i++) {
            actualResults[i] = originalTitleLineParser.parseLine(lines[i]);
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
        LineParser originalTitleLineParser = new OriginalTitleLineParser();
        final int TESTS_NO = lines.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> originalTitleLineParser.parseLine(lines[index.get()]));
        }
    }
    @Test
    public void shouldThrowMalformedLineException() {
        //given
        String[] lines = {"abc",
                "dsadasdsaczxcasdasdsadas",
                "31290dsa123930123129"};
        LineParser originalTitleLineParser = new OriginalTitleLineParser();
        final int TESTS_NO = lines.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(MalformedLineException.class,
                    () -> originalTitleLineParser.parseLine(lines[index.get()]));
        }
    }

}
