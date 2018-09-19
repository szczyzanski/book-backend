package szczyzanski.entities.builders.bn.catalog.parser.utilities.url.creator;

import java.net.MalformedURLException;
import java.net.URL;

public class BNSearchingEngineURLCreator implements URLCreator {
    @Override
    public URL createUrl(Object object, Object... args) throws MalformedURLException {
        final String URL_START = "http://katalogi.bn.org.pl/iii/encore/search/C__S";
        final String URL_END = "__Orightresult__U?lang=pol&suite=cobalt";
        return new URL(URL_START + object + URL_END);
    }
}
