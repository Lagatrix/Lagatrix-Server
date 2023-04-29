package lagatrix.exceptions.manager.hardware;

import lagatrix.exceptions.manager.ManagerException;

/**
 * This exception repersents all errors related to the GPU.
 *
 * @author javierfh03
 * @since 0.1
 */
public class GPUException extends ManagerException {
    
    public GPUException(String errorMessage) {
        super(errorMessage);
    }
    
}
