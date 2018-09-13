package szczyznaski.utilities.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.utilities.LineParser;
import szczyzanski.utilities.implementation.ISBNLineParser;

import java.util.concurrent.atomic.AtomicInteger;

public class ISBNLineParserTest {
    @Test
    public void shouldReturnISBN() throws MalformedLineException {
        //given
        String[] lines = {"020    9788365613059|czł 59 ",
                            "020    9788308065105|czł 39,90 ",
                            "020    9788308065228|czł 44,90",
                            "020    9788307034164 ",
                            "020    9788365613622|czł 35 ",
                            "020    9788328053571|czł 49,99 ",
                            "020    9788308065297|czł 34,90 "};
        String[] expectedResults = {"9788365613059",
                                    "9788308065105",
                                    "9788308065228",
                                    "9788307034164",
                                    "9788365613622",
                                    "9788328053571",
                                    "9788308065297"};
        String[] actualResults = new String[lines.length];
        LineParser isbnLineParser = new ISBNLineParser();
        final int TESTS_NO = lines.length;
        //when
        for(int i = 0; i < TESTS_NO; i++) {
            actualResults[i] = isbnLineParser.parseLine(lines[i]);
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
        LineParser isbnLineParser = new ISBNLineParser();
        final int TESTS_NO = lines.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> isbnLineParser.parseLine(lines[index.get()]));
        }
    }
    @Test
    public void shouldThrowMalformedLineException() {
        //given
        String[] lines = {"abc",
                            "dsadasdsaczxcasdasdsadas",
                            "31290dsa123930123129"};
        LineParser isbnLineParser = new ISBNLineParser();
        final int TESTS_NO = lines.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(MalformedLineException.class,
                    () -> isbnLineParser.parseLine(lines[index.get()]));
        }
    }
}
