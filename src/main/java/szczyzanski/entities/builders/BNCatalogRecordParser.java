package szczyzanski.entities.builders;

import org.apache.commons.lang3.StringUtils;
import szczyzanski.book.domain.entities.Book;
import szczyzanski.exceptions.BNRecordParsingException;
import szczyzanski.exceptions.NoRecordIdFoundException;
import szczyzanski.entities.builders.utilities.URLCreator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class BNCatalogRecordParser {

    private URL searchingEngineUrl;
    private URL recordFileUrl;
    private long isbn;
    private String recordNo;
    private Book book;

//    public static void main(String[] args) {
//        BNCatalogRecordParser recordParser = new BNCatalogRecordParser();
//        recordParser.setIsbn(9788365613059L);
//        try {
//            recordParser.findRecordByIsbn();
//        } catch (BNRecordParsingException e) {
//            e.printStackTrace();
//        }
//    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public Book findRecordByIsbn() throws BNRecordParsingException {
        try {
            createSearchingEngineUrl();
            isSearchingMachineUrlSet();
            findRecordIdNumber();
            createRecordFileUrl();
            runBookBuilder();
            return book;
        } catch (IOException|NoRecordIdFoundException e) {
            e.printStackTrace();
            throw new BNRecordParsingException("Cannot parse record!" + System.getProperty("line.separator")
                    + "Number: " + isbn + System.getProperty("line.separator")
                    + "URL: " + searchingEngineUrl);
        }
    }

    private void createSearchingEngineUrl() {
       try {
           searchingEngineUrl = URLCreator.createSearchingEngineUrl(isbn);
       } catch (MalformedURLException e) {
           //TODO loger?
           e.printStackTrace();
       }
    }

    private void createRecordFileUrl() {
        try {
            recordFileUrl = URLCreator.createRecordFileUrl(isbn, recordNo);
        } catch (MalformedURLException e) {
            //TODO loger?
            e.printStackTrace();
        }
    }

    private void isSearchingMachineUrlSet() {
       if(StringUtils.isBlank(searchingEngineUrl.toString())) {
           //TODO loger?
          throw new IllegalArgumentException("URL is not set");
       }
    }

    private void findRecordIdNumber() throws IOException, NoRecordIdFoundException {
        findIdNumberInFile(downloadFileContent(searchingEngineUrl));
    }

    private void findIdNumberInFile(String fileContent) throws NoRecordIdFoundException {
        String RECORD_IDENTIFIER = "resultRecord-".toLowerCase();
        if(fileContent.toLowerCase().contains(RECORD_IDENTIFIER)) {
            final int recordStart = fileContent.indexOf("resultRecord-") + "resultRecord-".length();
            fileContent = fileContent.substring(recordStart);
            final int recordEnd = fileContent.indexOf("\"");
            recordNo = fileContent.substring(0, recordEnd);
        } else {
            throw new NoRecordIdFoundException("No record id number found in file downloaded by searching engine");
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

   private void runBookBuilder() throws IOException {
        BookBuilder bookBuilder = new BookBuilder();
        book = bookBuilder.createBookFromFile(downloadFileContent(recordFileUrl));
   }
}