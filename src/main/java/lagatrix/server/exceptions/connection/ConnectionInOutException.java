package lagatrix.server.exceptions.connection;

import lagatrix.server.entities.actions.ActionsEnum;

/**
 * This exception repersents a connection with I/O problems.
 *
 * @author javierfh03
 * @since 0.2
 */
public class ConnectionInOutException extends ConnectionException {

    public ConnectionInOutException(String description) {
        super(description);
    }

    /**
     * This method obtain the error message.
     * 
     * @param communicatorClass The communicator who use.
     * @param action The action who done.
     * @return The message.
     */
    public static String getMessageIO(Class communicatorClass, ActionsEnum action) {
        return String.format("I/O problem when use %s connector and execute %s connection", 
                communicatorClass.getSimpleName(), action);
    }
}
