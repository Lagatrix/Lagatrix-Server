package lagatrix.server.exceptions.manager.os;

import lagatrix.server.exceptions.manager.ManagerException;

/**
 * This exception repersents all errors related to the information of OS.
 *
 * @author javier
 * @since 1.0
 */
public class OSException extends ManagerException{
    
    public OSException(String errorMessage) {
        super(errorMessage);
    }
    
}
