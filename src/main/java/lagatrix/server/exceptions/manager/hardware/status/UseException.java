package lagatrix.server.exceptions.manager.hardware.status;

import lagatrix.server.exceptions.manager.ManagerException;

/**
 *  This exception repersents all errors related to the use of devices.
 * 
 * @author javier
 * @since 1.0
 */
public class UseException extends ManagerException {
    
    public UseException(String errorMessage) {
        super(errorMessage);
    }
    
}
