package szczyznaski.utilities.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.utilities.LineParser;
import szczyzanski.utilities.implementation.NameLineParser;

import java.util.concurrent.atomic.AtomicInteger;

public class NameLineParserTest {
    @Test
    public void shouldReturnName() throws MalformedLineException {
        //given
        String[] lines = {"100 1  Dalrymple, William|d(1965- ).|eAutor ",
                            "100 1  Masłowska, Dorota|d(1983- ).|eAutor",
                            "100 1  Pamuk, Orhan|d(1952- ).|eAutor ",
                            "100 1  McGregor, Jon|d(1976- ).|eAutor ",
                            "100 1  Mrożek, Sławomir|d(1930-2013).|eAutor ",
                            "100 1  Chabon, Michael|d(1963- ).|eAutor ",
                            "100 1  Pilch, Jerzy|d(1952- ).|eAutor "};
        String[] expectedResults = {"William Dalrymple",
                                    "Dorota Masłowska",
                                    "Orhan Pamuk",
                                    "Jon McGregor",
                                    "Sławomir Mrożek",
                                    "Michael Chabon",
                                    "Jerzy Pilch"};
        String[] actualResults = new String[lines.length];
        LineParser nameLineParser = new NameLineParser();
        final int TESTS_NO = lines.length;
        //when
        for(int i = 0; i < TESTS_NO; i++) {
            actualResults[i] = nameLineParser.parseLine(lines[i]);
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
        LineParser nameLineParser = new NameLineParser();
        final int TESTS_NO = lines.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> nameLineParser.parseLine(lines[index.get()]));
        }
    }
    @Test
    public void shouldThrowMalformedArgumentException() {
        //given
        String[] lines = {"321412312312312312",
                "321312"};
        LineParser nameLineParser = new NameLineParser();
        final int TESTS_NO = lines.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(MalformedLineException.class,
                    () -> nameLineParser.parseLine(lines[index.get()]));
        }
    }
}
