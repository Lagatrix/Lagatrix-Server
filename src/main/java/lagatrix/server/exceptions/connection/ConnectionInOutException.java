package lagatrix.server.exceptions.connection;

import lagatrix.server.entities.actions.ActionsEnum;

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

    /**
     * This method obtain the error message.
     * 
     * @param algorithm The algorithm who used.
     * @param action The action who done.
     * @return The message.
     */
    public static String getMessageIO(String algorithm, ActionsEnum action) {
        return String.format("%s problem I/O when %s connection", algorithm, action);
    }
}
