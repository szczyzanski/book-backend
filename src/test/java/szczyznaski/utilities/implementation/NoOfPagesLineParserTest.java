package szczyznaski.utilities.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.utilities.LineParser;
import szczyzanski.utilities.implementation.NoOfPagesLineParser;

import java.util.concurrent.atomic.AtomicInteger;

public class NoOfPagesLineParserTest {
    @Test
    public void shouldReturnNoOfPages() throws MalformedLineException {
        //given
        String[] lines = {"300    689, [11] stron :|bilustracje ;|c22 cm.",
                "300    626, [2] strony :|bilustracja ;|c21 cm. ",
                "300    154, [6] stron :|bilustracje ;|c20x22 cm. ",
                "300    162, [6] stron ;|c19 cm.",
                "300    293, [3] strony ;|c20 cm. ",
                "300    750, [2] strony :|bilustracje ;|c21 cm. ",
                "300    197, [2] strony ;|c21 cm. ",
                "300    604, [4] strony :|bfaksymilia, fotografie, ilustracje, "};
        String[] expectedResults = {"689",
                "626",
                "154",
                "162",
                "293",
                "750",
                "197",
                "604"};
        String[] actualResults = new String[lines.length];
        LineParser nopLineParser = new NoOfPagesLineParser();
        final int TESTS_NO = lines.length;
        //when
        for(int i = 0; i < TESTS_NO; i++) {
            actualResults[i] = nopLineParser.parseLine(lines[i]);
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
        LineParser nopLineParser = new NoOfPagesLineParser();
        final int TESTS_NO = lines.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> nopLineParser.parseLine(lines[index.get()]));
        }
    }
    @Test
    public void shouldThrowMalformedLineException() {
        //given
        String[] lines = {"abc",
                            "kodsakodpsajdsajdoisajdaoisjdsoia"};
        LineParser nopLineParser = new NoOfPagesLineParser();
        final int TESTS_NO = lines.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(MalformedLineException.class,
                    () -> nopLineParser.parseLine(lines[index.get()]));
        }
    }
}
