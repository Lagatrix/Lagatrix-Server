package lagatrix.exceptions.connection;

import java.io.IOException;
import lagatrix.entities.actions.ActionsEnum;

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
     * @param ex The IOException.
     * @return The message.
     */
    public static String getMessageIO(Class communicatorClass, ActionsEnum action, IOException ex) {
        return String.format("I/O problem when use %s connector and execute %s connection because %s", 
                communicatorClass.getSimpleName(), action, ex.getMessage());
    }
}
