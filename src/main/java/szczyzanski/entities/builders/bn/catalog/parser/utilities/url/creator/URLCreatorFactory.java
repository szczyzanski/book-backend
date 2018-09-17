package szczyzanski.entities.builders.bn.catalog.parser.utilities.url.creator;

import szczyzanski.exceptions.NoSuchUrlCreatorTypeException;

public class URLCreatorFactory {
    public static URLCreator createURL(URLType urlType) throws NoSuchUrlCreatorTypeException {
        switch(urlType) {
            case RECORD_FILE:
                return new RecordFileURLCreator();
            case SEARCHING_ENGINGE:
                return new SearchingEngineURLCreator();
            default:
                throw new NoSuchUrlCreatorTypeException("No url creator for type: " + urlType);
        }
    }
}
