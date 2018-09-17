package szczyzanski.entities.builders.bn.catalog.parser.utilities.url.creator;

import java.net.MalformedURLException;
import java.net.URL;

public interface URLCreator {
    public URL createUrl(long isbn, Object... args) throws MalformedURLException;
}
