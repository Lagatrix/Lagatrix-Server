package lagatrix.server.exceptions.connection;

/**
 * This error is used if the client sends an invalid object.
 *
 * @author javier
 * @since 1.0
 */
public class BadClassFormatException extends ConnectionException {

    public BadClassFormatException(String description) {
        super(description);
    }

}
