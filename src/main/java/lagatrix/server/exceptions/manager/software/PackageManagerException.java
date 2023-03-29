package lagatrix.server.exceptions.manager.software;

import lagatrix.server.exceptions.manager.ManagerException;

/**
 * This exception repersents all errors related to the package manager.
 *
 * @author javier
 * @since 0.1
 */
public class PackageManagerException extends ManagerException {

    public PackageManagerException(String errorMessage) {
        super(errorMessage);
    }
    
}
