package szczyzanski.exceptions;

public class NoSuchLineParserException extends Exception {
    public NoSuchLineParserException(String message) {
        super(message);
    }
    public NoSuchLineParserException(String message, Exception e) {
        super(message, e);
    }
}
