package lagatrix.server.exceptions.manager.event;

import lagatrix.server.exceptions.manager.ManagerException;

/**
 * This exception repersents all errors related to the events.
 *
 * @author javierfh03
 * @since 0.1
 */
public class EventException extends ManagerException {

    public EventException(String errorMessage) {
        super(errorMessage);
    }

}
