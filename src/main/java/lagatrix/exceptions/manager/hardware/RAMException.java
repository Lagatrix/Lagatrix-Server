package lagatrix.exceptions.manager.hardware;

import lagatrix.exceptions.manager.ManagerException;

/**
 * This exception repersents all errors related to the RAM.
 *
 * @author javierfh03
 * @since 0.1
 */
public class RAMException extends ManagerException {
    
    public RAMException(String errorMessage) {
        super(errorMessage);
    }
    
}
