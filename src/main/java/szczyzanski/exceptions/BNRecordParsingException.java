package szczyzanski.exceptions;

public class BNRecordParsingException extends Exception {
    public BNRecordParsingException(String message) {
        super(message);
    }
    public BNRecordParsingException(String message, Exception e) {
        super(message, e);
    }
}
