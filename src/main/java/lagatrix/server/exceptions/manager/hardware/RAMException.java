package lagatrix.server.exceptions.manager.hardware;

import lagatrix.server.exceptions.manager.ManagerException;

/**
 * This exception repersents all errors related to the RAM.
 *
 * @author javier
 * @since 1.0
 */
public class RAMException extends ManagerException {
    
    public RAMException(String errorMessage) {
        super(errorMessage);
    }
    
}
