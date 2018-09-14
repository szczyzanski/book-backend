package szczyzanski.entities.builders.utilities;

import java.net.MalformedURLException;
import java.net.URL;

public class URLCreator {
    public static URL createSearchingEngineUrl(long isbn) throws MalformedURLException {
        final String URL_START = "http://katalogi.bn.org.pl/iii/encore/search/C__S";
        final String URL_END = "__Orightresult__U?lang=pol&suite=cobalt";
        return new URL(URL_START + isbn + URL_END);
    }
    public static URL createRecordFileUrl(long isbn, String recordNo) throws MalformedURLException {
        final String PRE_RECORD_URL = "http://katalogi.bn.org.pl/iii/encore/record/C__R";
        final String PRE_ISBN_URL = "__S";
        final String POST_ISBN_URL = "__Orightresult__U__X3?lang=pol&suite=cobalt&marcData=Y";
        return new URL(PRE_RECORD_URL + recordNo + PRE_ISBN_URL + isbn + POST_ISBN_URL);
    }
}
