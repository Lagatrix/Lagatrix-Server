package lagatrix.server.exceptions.connection;

import lagatrix.server.exceptions.LagatrixException;

/**
 * This exception represents the errors relates to the connection.
 *
 * @author javier
 * @since 1.0
 */
public class ConnectionException extends LagatrixException {

    public ConnectionException(String description) {
        super(description);
    }

    /**
     * This method generate the exception message.
     * 
     * @param failClass The class who fail.
     * @param message Description of action.
     * @return The message.
     */
    public static String getMessage(Class failClass, String message) {
        return String.format("Problem connection when %s in '%s' class", message, failClass.getSimpleName());
    }
}
