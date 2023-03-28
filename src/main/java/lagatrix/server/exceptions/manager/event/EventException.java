package lagatrix.server.exceptions.manager.event;

import lagatrix.server.exceptions.manager.ManagerException;

/**
 * This exception repersents all errors related to the events.
 *
 * @author javier
 * @since 1.0
 */
public class EventException extends ManagerException {

    public EventException(String errorMessage) {
        super(errorMessage);
    }

}
