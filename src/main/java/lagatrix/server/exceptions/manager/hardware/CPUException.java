package lagatrix.server.exceptions.manager.hardware;

import lagatrix.server.exceptions.manager.ManagerException;

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
