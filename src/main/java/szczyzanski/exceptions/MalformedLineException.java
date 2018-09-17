package szczyzanski.exceptions;

public class MalformedLineException extends Exception {
    public MalformedLineException(String message) {
        super(message);
    }
    public MalformedLineException(String message, Exception e) {
        super(message, e);
    }
}
