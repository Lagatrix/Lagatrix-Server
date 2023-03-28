package lagatrix.server.exceptions.manager.hardware;

import lagatrix.server.exceptions.manager.ManagerException;

/**
 * This exception repersents all errors related to the CPU.
 * 
 * @author javier
 * @since 1.0
 */
public class CPUException extends ManagerException {
    
    public CPUException(String errorMessage) {
        super(errorMessage);
    }
    
}
