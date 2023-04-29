package lagatrix.exceptions.manager.hardware.status;

import lagatrix.exceptions.manager.ManagerException;

/**
 *  This exception repersents all errors related to the use of devices.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class UseException extends ManagerException {
    
    public UseException(String errorMessage) {
        super(errorMessage);
    }
    
}
