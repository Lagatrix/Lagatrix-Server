package lagatrix.exceptions.manager.hardware;

import lagatrix.exceptions.manager.ManagerException;

/**
 * This exception repersents all errors related to the CPU.
 * 
 * @author javierfh03
 * @since 0.1
 */
public class CPUException extends ManagerException {
    
    public CPUException(String errorMessage) {
        super(errorMessage);
    }
    
}
