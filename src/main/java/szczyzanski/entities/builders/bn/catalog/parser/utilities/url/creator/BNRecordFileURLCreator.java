package szczyzanski.entities.builders.bn.catalog.parser.utilities.url.creator;

import java.net.MalformedURLException;
import java.net.URL;

public class BNRecordFileURLCreator implements URLCreator{
    @Override
    public URL createUrl(Object object, Object... args) throws MalformedURLException {
        final String PRE_RECORD_URL = "http://katalogi.bn.org.pl/iii/encore/record/C__R";
        final String PRE_ISBN_URL = "__S";
        final String POST_ISBN_URL = "__Orightresult__U__X3?lang=pol&suite=cobalt&marcData=Y";
        return new URL(PRE_RECORD_URL + args[0] + PRE_ISBN_URL + object + POST_ISBN_URL);
    }
}
