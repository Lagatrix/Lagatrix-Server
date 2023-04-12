package lagatrix.server.exceptions.connection;

import lagatrix.server.exceptions.LagatrixException;

/**
 * This exception represents the errors relates to the connection.
 *
 * @author javierfh03
 * @since 0.2
 */
public class ConnectionException extends LagatrixException {

    public ConnectionException(String description) {
        super(description);
    }
}
