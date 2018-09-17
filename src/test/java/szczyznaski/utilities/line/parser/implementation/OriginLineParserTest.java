package szczyznaski.utilities.line.parser.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.LineParser;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.implementation.OriginLineParser;

import java.util.concurrent.atomic.AtomicInteger;

public class OriginLineParserTest {
    @Test
    public void shouldReturnOrigin() throws MalformedLineException {
        //given
        String[] lines = {"386    |mPrzynależność kulturowa|aLiteratura szkocka",
                            "386    |mPrzynależność kulturowa|aLiteratura polska",
                            "386    |mPrzynależność kulturowa|aLiteratura turecka ",
                            "386    |mPrzynależność kulturowa|aLiteratura angielska ",
                            "386    |mPrzynależność kulturowa|aLiteratura amerykańska "};
        String[] expectedResults = {"Literatura szkocka",
                                    "Literatura polska",
                                    "Literatura turecka",
                                    "Literatura angielska",
                                    "Literatura amerykańska"};
        String[] actualResults = new String[lines.length];
        LineParser originLineParser = new OriginLineParser();
        final int TESTS_NO = lines.length;
        //when
        for(int i = 0; i < TESTS_NO; i++) {
            actualResults[i] = originLineParser.parseLine(lines[i]);
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
        LineParser originLineParser = new OriginLineParser();
        final int TESTS_NO = lines.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> originLineParser.parseLine(lines[index.get()]));
        }
    }
    @Test
    public void shouldThrowMalformedLineException() {
        //TODO second line?
        //TODO excpetions packing
        //given
        String[] lines = {"abc",
                "dsadasdsaczxcasdasdsadas",
                "31290dsa123930123129"};
        LineParser originLineParser = new OriginLineParser();
        final int TESTS_NO = lines.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(MalformedLineException.class,
                    () -> originLineParser.parseLine(lines[index.get()]));
        }
    }
}
