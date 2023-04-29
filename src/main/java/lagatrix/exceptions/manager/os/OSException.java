package lagatrix.exceptions.manager.os;

import lagatrix.exceptions.manager.ManagerException;

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
