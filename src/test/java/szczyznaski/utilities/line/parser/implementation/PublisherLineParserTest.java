package szczyznaski.utilities.line.parser.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.LineParser;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.implementation.PublisherLineParser;

import java.util.concurrent.atomic.AtomicInteger;

public class PublisherLineParserTest {
    @Test
    public void shouldReturnPublisher() throws MalformedLineException {
        //given
        String[] lines = {"260    Kraków :|bWydawnictwo Literackie,|c2018.",
                            "260    Warszawa :|bWydawnictwo W.A.B. - Grupa Wydawnicza Foksal,|c2018. ",
                            "260    Warszawa :|bOficyna Literacka Noir sur Blanc,|c2018. ",
                            "260    Warszawa :|bSpółdzielnia Wydawnicza \"Czytelnik\",|ccopyright 2018. ",
                            "260    Kraków :|bWydawnictwo Literackie,|c2018. ",
                            "260    Kraków :|bWydawnictwo Literackie,|c2018. ",
                            "260    Warszawa :|bOficyna Literacka Noir sur Blanc,|c2017.",
                            "260    Warszawa :|bWydawnictwo W.A.B. - Grupa Wydawnicza Foksal,\n" +
                                    "       |c2018."};
        String[] expectedResults = {"Wydawnictwo Literackie",
                                    "Wydawnictwo W.A.B. - Grupa Wydawnicza Foksal",
                                    "Oficyna Literacka Noir sur Blanc",
                                    "Spółdzielnia Wydawnicza \"Czytelnik\"",
                                    "Wydawnictwo Literackie",
                                    "Wydawnictwo Literackie",
                                    "Oficyna Literacka Noir sur Blanc",
                                    "Wydawnictwo W.A.B. - Grupa Wydawnicza Foksal"};
        String[] actualResults = new String[lines.length];
        LineParser publisherLineParser = new PublisherLineParser();
        final int TESTS_NO = lines.length;
        //when
        for(int i = 0; i < TESTS_NO; i++) {
            actualResults[i] = publisherLineParser.parseLine(lines[i]);
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
        LineParser publisherLineParser = new PublisherLineParser();
        final int TESTS_NO = lines.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> publisherLineParser.parseLine(lines[index.get()]));
        }
    }
    @Test
    public void shouldThrowMalformedLineException() {
        //given
        String[] lines = {"abc",
                "dsadasdsaczxcasdasdsadas",
                "31290dsa123930123129"};
        LineParser publisherLineParser = new PublisherLineParser();
        final int TESTS_NO = lines.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(MalformedLineException.class,
                    () -> publisherLineParser.parseLine(lines[index.get()]));
        }
    }
}
