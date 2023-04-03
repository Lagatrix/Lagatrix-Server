package lagatrix.server.exceptions.connection;

/**
 * This exception repersents a connection with I/O problems.
 *
 * @author javier
 * @since 1.0
 */
public class ConnectionInOutException extends ConnectionException {

    public ConnectionInOutException(String description) {
        super(description);
    }

}
