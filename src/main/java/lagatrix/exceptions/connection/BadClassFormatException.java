package lagatrix.exceptions.connection;

/**
 * This error is used if the client sends an invalid object.
 *
 * @author javierfh03
 * @since 0.2
 */
public class BadClassFormatException extends ConnectionException {

    public BadClassFormatException() {
        super("Unexpected class");
    }

}
