package lagatrix.server.exceptions.manager.process;

import lagatrix.server.exceptions.manager.ManagerException;

/**
 * This exception repersents all errors related to the process.
 *
 * @author javier
 * @since 1.0
 */
public class ProcessException extends ManagerException {
    
    public ProcessException(String errorMessage) {
        super(errorMessage);
    }
    
}
