package szczyzanski.exceptions;

public class NoSuchUrlCreatorTypeException extends Exception {
    public NoSuchUrlCreatorTypeException(String message) {
        super(message);
    }
    public NoSuchUrlCreatorTypeException(String message, Exception e) {
        super(message, e);
    }
}
