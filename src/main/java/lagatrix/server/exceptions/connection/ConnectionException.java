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
     * @param ip The ip of teh client.
     * @return The message.
     */
    public static String getMessage(Class failClass, String message, String ip) {
        return String.format("Problem connection because %s in '%s' class with client %s", 
                message, failClass.getSimpleName(), ip);
    }
}
