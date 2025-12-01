package exceptions;

public class InvalidOperationException extends RuntimeException {

    public InvalidOperationException(String messageString) {
        super(messageString);
    }

}
