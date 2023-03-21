package lagatrix.server.exceptions.manager;

import lagatrix.server.exceptions.LagatrixServerException;

/**
 * This exception repersents all errors related to the managers.
 * 
 * @author javier
 * @since 1.0
 */
public class ManagerException extends LagatrixServerException{

    public ManagerException(String description) {
        super(description);
    }

}
