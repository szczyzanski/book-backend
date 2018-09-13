package szczyznaski.utilities;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import szczyzanski.exceptions.NoSuchLineParserException;
import szczyzanski.utilities.LineParser;
import szczyzanski.utilities.ParserFactory;
import szczyzanski.utilities.implementation.*;
import szczyzanski.utilities.implementation.ISBNLineParser;
import szczyzanski.utilities.implementation.NameLineParser;
import szczyzanski.utilities.implementation.TagLineParser;

import java.util.concurrent.atomic.AtomicInteger;

public class LineParserFactoryTest {
    @Test
    public void shouldReturnProperParser() throws NoSuchLineParserException {
        //given
        String[] codes = {"020",
                            "100",
                            "245",
                            "246",
                            "260",
                            "300",
                            "380",
                            "650",
                            "651",
                            "655",
                            "658",
                            "700"};
        LineParser[] expectedLineParsers = {new ISBNLineParser(),
                                    new NameLineParser(),
                                    new TitleLineParser(),
                                    new OriginalTitleLineParser(),
                                    new PublisherLineParser(),
                                    new NoOfPagesLineParser(),
                                    new TagLineParser(),
                                    new TagLineParser(),
                                    new TagLineParser(),
                                    new TagLineParser(),
                                    new TagLineParser(),
                                    new NameLineParser(),};
        LineParser[] actualLineParsers = new LineParser[expectedLineParsers.length];
        final int TESTS_NO = codes.length;
        //when
        for(int i = 0; i < TESTS_NO; i++) {
            actualLineParsers[i] = ParserFactory.chooseParser(codes[i], null);
        }
        //then
        for(int i = 0; i < TESTS_NO; i++) {
            Assertions.assertEquals(expectedLineParsers[i].getClass(), actualLineParsers[i].getClass());
        }
    }
    @Test
    public void shouldThrowNoSuchParserException() {
        //given
        String[] codes = {"123",
                            "xxx"};
        final int TESTS_NO = codes.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(NoSuchLineParserException.class,
                    () -> ParserFactory.chooseParser(codes[index.get()], null));
        }
    }
    @Test public void shouldThrowIllegalArgumentException() {
        //given
        String[] codes = {"",
                null};
        final int TESTS_NO = codes.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> ParserFactory.chooseParser(codes[index.get()], null));
        }
    }
}
