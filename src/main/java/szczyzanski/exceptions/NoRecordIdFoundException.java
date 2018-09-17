package szczyzanski.exceptions;

public class NoRecordIdFoundException extends Exception {
    public NoRecordIdFoundException(String message) {
        super(message);
    }
    public NoRecordIdFoundException(String message, Exception e) {
        super(message, e);
    }
}
