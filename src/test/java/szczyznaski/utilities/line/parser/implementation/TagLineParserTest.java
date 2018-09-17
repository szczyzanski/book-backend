package szczyznaski.utilities.line.parser.implementation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import szczyzanski.exceptions.MalformedLineException;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.LineParser;
import szczyzanski.entities.builders.bn.catalog.parser.line.parser.implementation.TagLineParser;

import java.util.concurrent.atomic.AtomicInteger;

public class TagLineParserTest {
    @Test
    public void shouldReturnTag() throws MalformedLineException {
        //given
        String[] lines = {"380    Książki",
                            "380    Literatura faktu, eseje, publicystyka",
                            "380    Poezja",
                            "380    Proza",
                            "650  4 Pisarze polscy ",
                            "650  4 Rzeczywistość postapokaliptyczna ",
                            "650  4 Tożsamość osobista ",
                            "651  4 Warszawa (woj. mazowieckie) ",
                            "655  4 Powieść ",
                            "650  4 II wojna światowa (1939-1945)",
                            "650  4 Dziadkowie i wnuki ",
                            "650  4 Chorzy w stanach terminalnych ",
                            "650  4 Loty kosmiczne",
                            "650  4 Mężczyzna ",
                            "650  4 Miłość ",
                            "650  4 Modelarstwo ",
                            "650  4 Pamięć autobiograficzna ",
                            "650  4 Sekrety rodzinne",
                            "651  4 Stany Zjednoczone (USA)",
                            "650  4 Urzędnicy",
                            "655  4 Opowiadania i nowele",
                            "650  4 Dziewczęta ",
                            "650  4 Osoby zaginione",
                            "650  4 Społeczności lokalne",
                            "651  4 Stambuł (Turcja)",
                            "655  4 Poemat ",
                            "658    Historia",
                            "658    Religia i duchowość"};
        String[] expectedResults = {"Książki",
                                    "Literatura faktu, eseje, publicystyka",
                                    "Poezja",
                                    "Proza",
                                    "Pisarze polscy",
                                    "Rzeczywistość postapokaliptyczna",
                                    "Tożsamość osobista",
                                    "Warszawa (woj. mazowieckie)",
                                    "Powieść",
                                    "II wojna światowa (1939-1945)",
                                    "Dziadkowie i wnuki",
                                    "Chorzy w stanach terminalnych",
                                    "Loty kosmiczne",
                                    "Mężczyzna",
                                    "Miłość",
                                    "Modelarstwo",
                                    "Pamięć autobiograficzna",
                                    "Sekrety rodzinne",
                                    "Stany Zjednoczone (USA)",
                                    "Urzędnicy",
                                    "Opowiadania i nowele",
                                    "Dziewczęta",
                                    "Osoby zaginione",
                                    "Społeczności lokalne",
                                    "Stambuł (Turcja)",
                                    "Poemat",
                                    "Historia",
                                    "Religia i duchowość"};
        String[] actualResults = new String[lines.length];
        LineParser tagLineParser = new TagLineParser();
        final int TESTS_NO = lines.length;
        //when
        for(int i = 0; i < TESTS_NO; i++) {
            actualResults[i] = tagLineParser.parseLine(lines[i]);
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
        LineParser tagLineParser = new TagLineParser();
        final int TESTS_NO = lines.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(IllegalArgumentException.class,
                    () -> tagLineParser.parseLine(lines[index.get()]));
        }
    }
    @Test
    public void shouldThrowMalformedLineException() {
        //given
        String[] lines = {"31290123930123129"};
        LineParser tagLineParser = new TagLineParser();
        final int TESTS_NO = lines.length;
        //when + then
        for(int i = 0; i < TESTS_NO; i++) {
            AtomicInteger index = new AtomicInteger(i);
            Assertions.assertThrows(MalformedLineException.class,
                    () -> tagLineParser.parseLine(lines[index.get()]));
        }
    }
}
