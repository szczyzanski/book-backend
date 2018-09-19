package szczyzanski.entities.builders.bn.catalog.parser.utilities.url.creator;

import szczyzanski.exceptions.NoSuchUrlCreatorTypeException;

public class URLCreatorFactory {
    public static URLCreator createURL(URLType urlType) throws NoSuchUrlCreatorTypeException {
        switch(urlType) {
            case BN_RECORD_FILE:
                return new BNRecordFileURLCreator();
            case BN_SEARCHING_ENGINGE:
                return new BNSearchingEngineURLCreator();
            case LC_SEARCHING_ENGINE:
                return new LCSearchingEngineURLCreator();
            default:
                throw new NoSuchUrlCreatorTypeException("No url creator for type: " + urlType);
        }
    }
}
