package console.twiddle.command;

/**
 * Exception thown when a manadatory parameter is null.
 * Created by akumar on 6/18/2016.
 */
public class NullArgumentException extends RuntimeException {

    /**
     * Construct a null argument exception.
     * @param message
     */
    public NullArgumentException(String message) {
        super(message);
    }
}
