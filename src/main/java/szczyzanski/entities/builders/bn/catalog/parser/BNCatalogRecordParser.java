package szczyzanski.entities.builders.bn.catalog.parser;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import szczyzanski.book.api.dto.full.book.BookWithFullInfoDTO;
import szczyzanski.entities.builders.bn.catalog.parser.utilities.url.creator.URLCreatorFactory;
import szczyzanski.entities.builders.bn.catalog.parser.utilities.url.creator.URLType;
import szczyzanski.exceptions.BNRecordParsingException;
import szczyzanski.exceptions.NoRecordIdFoundException;
import szczyzanski.exceptions.NoSuchUrlCreatorTypeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class BNCatalogRecordParser {

    final private static Logger logger = LoggerFactory.getLogger(BNCatalogRecordParser.class);
    private URL searchingEngineUrl;
    private URL recordFileUrl;
    private long isbn;
    private String recordNo;
    private BookWithFullInfoDTO bookWithFullInfoDTO;

//    public static void main(String[] args) {
//        BNCatalogRecordParser recordParser = new BNCatalogRecordParser();
//        recordParser.setIsbn(9788365613059L);
//        try {
//            recordParser.findRecordByIsbn();
//        } catch (BNRecordParsingException e) {
//            e.printStackTrace();
//        }
//    }


    public BNCatalogRecordParser(long isbn) {
        this.isbn = isbn;
    }

    public BookWithFullInfoDTO findRecordByIsbn() throws BNRecordParsingException {
        try {
            createSearchingEngineUrl();
            isUrlSet(searchingEngineUrl);
            findRecordIdNumber();
            createRecordFileUrl();
            isUrlSet(recordFileUrl);
            runBookBuilder();
            return bookWithFullInfoDTO;
        } catch (IOException|NoRecordIdFoundException e) {
            final String MSG = "Cannot parse record!" + System.getProperty("line.separator")
                    + "Number: " + isbn + System.getProperty("line.separator")
                    + "URL: " + searchingEngineUrl;
            BNRecordParsingException bnrpException = new BNRecordParsingException(MSG, e);
            logger.error(MSG, bnrpException);
            throw bnrpException;
        }
    }

    private void createSearchingEngineUrl() {
       try {
           searchingEngineUrl = URLCreatorFactory.createURL(URLType.BN_SEARCHING_ENGINGE).createUrl(isbn);
       } catch (MalformedURLException|NoSuchUrlCreatorTypeException e) {
           logger.error(e.getMessage(), e);
       }
    }

    private void createRecordFileUrl() {
        try {
            recordFileUrl = URLCreatorFactory.createURL(URLType.BN_RECORD_FILE).createUrl(isbn, recordNo);
        } catch (MalformedURLException|NoSuchUrlCreatorTypeException e) {
            logger.error(e.getMessage(), e);
        }
    }

    private void isUrlSet(URL url) {
       if(StringUtils.isBlank(url.toString())) {
          logger.error("URL is not set!");
          throw new IllegalArgumentException("URL is not set");
       }
    }

    private void findRecordIdNumber() throws IOException, NoRecordIdFoundException {
        findIdNumberInFile(downloadFileContentWithBreak(searchingEngineUrl));
    }

    private void findIdNumberInFile(String fileContent) throws NoRecordIdFoundException {
        final String RECORD_IDENTIFIER = "resultRecord-".toLowerCase();
        if(fileContent.toLowerCase().contains(RECORD_IDENTIFIER)) {
            final int recordStart = fileContent.indexOf("resultRecord-") + "resultRecord-".length();
            fileContent = fileContent.substring(recordStart);
            final int recordEnd = fileContent.indexOf("\"");
            recordNo = fileContent.substring(0, recordEnd);
        } else {
            final String MSG = "No record id number found in file downloaded by searching engine";
            NoRecordIdFoundException nrifException = new NoRecordIdFoundException(MSG);
            logger.error(MSG, nrifException);
            throw nrifException;
        }
    }

   private String downloadFileContent(URL url) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line, fileContent = "";
            while((line = br.readLine()) != null) {
                fileContent += line + System.getProperty("line.separator");
            }
            return fileContent;
        }
   }

    private String downloadFileContentWithBreak(URL url) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line, fileContent = "";
            while((line = br.readLine()) != null) {
                fileContent += line + System.getProperty("line.separator");
                if(line.contains("resultRecord-")) {
                    break;
                }
            }
            return fileContent;
        }
    }

   private void runBookBuilder() throws IOException {
        BookBuilder bookBuilder = new BookBuilder();
        bookWithFullInfoDTO = bookBuilder.createBookFromFile(downloadFileContent(recordFileUrl));
   }
}