package szczyzanski.exceptions;

public class NoSurnameFoundException extends Exception {
    public NoSurnameFoundException(String message) {
        super(message);
    }
    public NoSurnameFoundException(String message, Exception e) {
        super(message, e);
    }
}
