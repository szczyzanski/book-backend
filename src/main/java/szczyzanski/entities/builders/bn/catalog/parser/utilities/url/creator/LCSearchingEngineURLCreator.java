package szczyzanski.entities.builders.bn.catalog.parser.utilities.url.creator;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

public class LCSearchingEngineURLCreator implements URLCreator {
    @Override
    public URL createUrl(Object object, Object... args) throws MalformedURLException {
        final String PRE_TITLE_URL = "http://lubimyczytac.pl/szukaj/ksiazki?phrase=";
        final String POST_TITLE_URL = "&main_search=1";
        final String title = formTitle(object);
        return new URL(PRE_TITLE_URL + title + POST_TITLE_URL);
    }

    private String formTitle(Object object) {
        String title = (String) object;
        String[] splitedTitle = title.split("\\s+");
        String result = "";
        for(String word : splitedTitle) {
            result += word + "+";
        }
        if(result.endsWith("?")) {
            result = result.substring(0, result.length() - 1);
        }
        return result.toLowerCase();
    }
}
